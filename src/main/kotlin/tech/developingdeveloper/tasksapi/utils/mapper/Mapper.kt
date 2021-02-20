package tech.developingdeveloper.tasksapi.utils.mapper


/**
 * Created by Abhishek Saxena on 14-02-2021.
 */

interface Mapper<D, E> {
    fun fromEntity(entity: E): D
    fun toEntity(domain: D): E
}