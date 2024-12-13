package com.vadzim.yeumushkou.core.di.deps

import android.app.Activity
import android.app.Application
import android.app.Service
import android.view.View
import android.view.ViewParent
import androidx.fragment.app.Fragment

interface ComponentDependencies

typealias ComponentDependenciesProvider = Map<Class<out ComponentDependencies>, @JvmSuppressWildcards ComponentDependencies>

interface HasComponentDependencies {

    val dependencies: ComponentDependenciesProvider
}

inline fun <reified T : ComponentDependencies> Application.findComponentDependencies() =
    dependenciesProviderHolder.get<T>()

inline fun <reified T : ComponentDependencies> Activity.findComponentDependencies() =
    dependenciesProviderHolder.get<T>()

inline fun <reified T : ComponentDependencies> Service.findComponentDependencies() =
    dependenciesProviderHolder.get<T>()

inline fun <reified T : ComponentDependencies> View.findComponentDependencies() =
    dependenciesProviderHolder.get<T>()

inline fun <reified T : ComponentDependencies> Fragment.findComponentDependencies() =
    dependenciesProviderHolder.get<T>()

inline fun <reified T : ComponentDependencies> HasComponentDependencies.get(): T =
    dependencies[T::class.java] as? T ?: depsNotFound<T>(this)

inline fun <reified T : ComponentDependencies> depsNotFound(dependenciesHolder: Any): Nothing =
    throw IllegalStateException("Dependencies ${T::class.java.name} not found in $dependenciesHolder")

val Application.dependenciesProviderHolder
    get() = this as HasComponentDependencies

val Activity.dependenciesProviderHolder
    get() = application as HasComponentDependencies

val Service.dependenciesProviderHolder
    get() = application as HasComponentDependencies

val View.dependenciesProviderHolder: HasComponentDependencies
    get() {
        var ancestor: ViewParent? = parent
        while (ancestor !is HasComponentDependencies?) {
            ancestor = ancestor?.parent
        }

        return ancestor
            ?: context as? HasComponentDependencies
            ?: context.applicationContext as? HasComponentDependencies
            ?: throw IllegalStateException("Can not find suitable dagger provider for $this")
    }

val Fragment.dependenciesProviderHolder: HasComponentDependencies
    get() {
        var ancestor: Fragment? = parentFragment
        while (ancestor !is HasComponentDependencies?) {
            ancestor = ancestor?.parentFragment
        }

        return ancestor
            ?: activity as? HasComponentDependencies
            ?: activity?.application as? HasComponentDependencies
            ?: throw IllegalStateException("Can not find suitable dagger provider for $this")
    }