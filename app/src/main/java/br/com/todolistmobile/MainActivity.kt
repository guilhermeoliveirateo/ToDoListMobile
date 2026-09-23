package br.com.todolistmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.todolistmobile.ui.theme.ToDoListMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListMobileTheme {
                ToDoList()
            }
        }
    }
}

data class Task (
    val id: Long,
    val name: String,
    val isCompleted: Boolean = false
)

@Composable
fun ToDoList() {

    var task by remember { mutableStateOf(value = "") }
    var tasks by remember {mutableStateOf(value = listOf<Task>())}

    fun addTask() {
        if (task.isEmpty()) {
            return
        }
        else {
            tasks + Task(id = tasks.size.toLong(), name = task)
            task = ""
        }
    }

    fun completeTask(
        item: Task,
        checked: Boolean
    ) {
        tasks = tasks.map { if (it.id == item.id) it.copy(isCompleted = checked) else it }
    }

    fun clearFinishedTasks() {

    }

    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(space = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "To Do List", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = task,
            onValueChange = { v -> task = v.filter { it.isDigit() || it.isLetter() || it.isWhitespace()} },
            label = { Text(text = "Type a task") },
            trailingIcon = {
                Button(onClick = {addTask()}) {
                    Text(text = "Add")
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "test")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoListMobileTheme {
        ToDoList()
    }
}