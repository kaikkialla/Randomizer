package com.example.randomizer.ui.main

import androidx.lifecycle.Observer
import com.example.randomizer.model.Item
import com.example.randomizer.repository.Repository
import com.example.randomizer.repository.RepositoryImpl
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

class MainPresenter(
    override var view: MainContract.View? = null,
    private val repository: Repository
    ) : MainContract.Presenter {


    override fun onClick(from: Long, to: Long) {
        val value = generate(from, to)
        view?.setValue(value)

        repository.add(
            Item(
                repository.generateHash(),
                value,
                System.currentTimeMillis(),
                from,
                to
            )
        )
    }

    override fun onPause() {
        repository.save()
        view?.let {view ->
            view.saveValue(view.getValue())
        }
    }


    override fun onResume() {
        view?.let {view ->
            view.setValue(view.loadValue())
            repository.getlist().observe(view, Observer {})
        }
    }

    override fun generate(from: Long, to: Long): Long {
        val callable = Callable {
            (from..to).random()
        }

        val future = FutureTask<Long>(callable)
        Thread(future).start()
        return future.get()
    }

    override fun onDestroy() {
        view = null
    }
}