package br.com.todolistmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.todolistmobile.ui.theme.ToDoListMobileTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.ui.Alignment

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
    var nextId by remember { mutableLongStateOf(value = 0L) }

    fun addTask() {
        val name = task.trim()
        if (name.none { it.isLetter() }) return
        tasks = tasks + Task(id = nextId, name = name)
        nextId++
        task = ""
    }

    fun completeTask(
        item: Task,
        checked: Boolean
    ) {
        tasks = tasks.map { if (it.id == item.id) it.copy(isCompleted = checked) else it }
    }

    fun clearFinishedTasks() {
        tasks = tasks.filter { !it.isCompleted }
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

        LazyColumn (
            Modifier.weight(1f, fill = false).fillMaxWidth(),
        ) {
            items(tasks, key = { it.id }) { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = item.isCompleted,
                        onCheckedChange = { checked -> completeTask(item, checked)}
                    )
                    Text(text = item.name)
                }
            }
        }

        OutlinedButton(
            onClick = { clearFinishedTasks() },
            enabled = tasks.any { it.isCompleted },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.Black
            ),
            border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp),
            modifier = Modifier.fillMaxWidth()
        ) { Text(text = "Clear finished tasks", style = MaterialTheme.typography.labelLarge) }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoListMobileTheme {
        ToDoList()
    }
}