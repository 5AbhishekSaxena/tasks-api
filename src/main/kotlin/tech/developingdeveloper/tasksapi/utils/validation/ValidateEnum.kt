package tech.developingdeveloper.tasksapi.utils.validation

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import kotlin.reflect.KClass


@MustBeDocumented
@ReportAsSingleViolation
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [EnumValidator::class])
annotation class ValidateEnum(
    val message: String = "Must be one of the {enum}",
    val enumClass: KClass<out Enum<*>>,
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)