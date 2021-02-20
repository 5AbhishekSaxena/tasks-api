package tech.developingdeveloper.tasksapi.utils.validation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EnumValidator : ConstraintValidator<ValidateEnum, CharSequence> {

    private lateinit var values: List<String>

    override fun initialize(constraintAnnotation: ValidateEnum) {
        values = constraintAnnotation.enumClass.java.enumConstants.map { it.toString().toUpperCase() }
    }

    override fun isValid(value: CharSequence?, context: ConstraintValidatorContext?): Boolean = value?.let {
        values.contains(it)
    } ?: true
}
