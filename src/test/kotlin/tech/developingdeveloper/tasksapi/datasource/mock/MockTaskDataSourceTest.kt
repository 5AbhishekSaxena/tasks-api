package tech.developingdeveloper.tasksapi.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockTaskDataSourceTest {

    private val mockDataSource = MockTaskDataSource()

    @Test
    fun `should provide a collection of tasks`() {

        // when
        val tasks = mockDataSource.retrieveTasks()

        // then
        assertThat(tasks).isNotEmpty

    }
}