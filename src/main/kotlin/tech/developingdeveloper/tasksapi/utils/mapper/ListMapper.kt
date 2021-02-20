package tech.developingdeveloper.tasksapi.utils.mapper


/**
 * Created by Abhishek Saxena on 14-02-2021.
 */

interface ListMapper<D, E> {

    fun fromEntityList(entityList: Collection<E>): Collection<D>
    fun toEntityList(domainList: Collection<D>): Collection<E>
}