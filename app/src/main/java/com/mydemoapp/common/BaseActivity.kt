package com.mydemoapp.common

import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.mydemoapp.MyDemoApp
import com.mydemoapp.common.utils.Constants
import com.mydemoapp.common.utils.Utils

abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {
    protected abstract fun defineLayoutResource(): Int

    protected abstract fun initializeComponent() //to initialize the activity components

    protected abstract fun initToolbar()

    private var lastClickedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(defineLayoutResource())

        initToolbar()
        initializeComponent()
    }

    /**
     * Method to get fragment manger.
     */
    fun getLocalFragmentManager(): FragmentManager {
        return this.supportFragmentManager
    }

    /**
     * Adds the Fragment into layout container
     *
     * @param fragmentContainerResourceId Resource id of the layout in which Fragment will be added
     * @param currentFragment             Current loaded Fragment to be hide
     * @param nextFragment                New Fragment to be loaded into fragmentContainerResourceId
     * @param commitAllowingStateLoss     true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     * @throws IllegalStateException Exception if Fragment transaction is invalid
     */
    fun addFragment(container: Int, currentFragment: Fragment, nextFragment: Fragment, commitAllowingStateLoss: Boolean) {

        val fragmentTransaction = getLocalFragmentManager().beginTransaction()
        fragmentTransaction.apply {
            this.add(container, nextFragment, nextFragment.javaClass.simpleName)
            this.addToBackStack(nextFragment.javaClass.simpleName)

            val parentFragment = currentFragment.parentFragment
            this.hide(parentFragment ?: currentFragment)

            if (!commitAllowingStateLoss) this.commit()
            else this.commitAllowingStateLoss()
        }
    }


    /**
     * Replaces the Fragment into layout container
     *
     * @param fragmentContainerResourceId Resource id of the layout in which Fragment will be added
     * @param nextFragment                New Fragment to be loaded into fragmentContainerResourceId
     * @param commitAllowingStateLoss     true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     * @throws IllegalStateException Exception if Fragment transaction is invalid
     */
    fun replaceFragment(container: Int, nextFragment: Fragment, commitAllowingStateLoss: Boolean) {
        val fragmentTransaction = getLocalFragmentManager().beginTransaction()
        fragmentTransaction.apply {
            this.replace(container, nextFragment, nextFragment.javaClass.simpleName)

            if (!commitAllowingStateLoss) {
                this.commit()
            } else {
                this.commitAllowingStateLoss()
            }
        }
    }

    override fun onClick(view: View) {
//        Utils.hideSoftKeyBoard(MyDemoApp.instance, view)
        /*
         * Logic to Prevent the Launch of the Fragment Twice if User makes
         * the Tap(Click) very Fast.
         */
        if (SystemClock.elapsedRealtime() - lastClickedTime < Constants.MAX_CLICK_INTERVAL) {
            return
        }
        lastClickedTime = SystemClock.elapsedRealtime()
    }
}