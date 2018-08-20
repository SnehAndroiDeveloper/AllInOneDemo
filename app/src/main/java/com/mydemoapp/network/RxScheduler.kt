package com.mydemoapp.network

import rx.Scheduler

interface RxScheduler {
    fun runOnBackground(): Scheduler

    fun io(): Scheduler

    fun compute(): Scheduler

    fun androidThread(): Scheduler

    fun internet(): Scheduler
}