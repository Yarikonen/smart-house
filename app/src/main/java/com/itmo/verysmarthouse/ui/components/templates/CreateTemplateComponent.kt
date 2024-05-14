package com.itmo.verysmarthouse.ui.components.templates

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
@Composable
fun <T> CreateTemplateComponent(
    dialogOpen: MutableState<Boolean>,
    onClick: (String) -> Unit,
    suggestions: Array<T>,
    selectedElement: T,
    getStringValue: (T) -> String,
    onValueChange: (T) -> Unit,
    labelName: String,
    labelType: String
) {
    var name by remember {
        mutableStateOf("")
    }
    if(dialogOpen.value){
        Dialog(
            onDismissRequest = { dialogOpen.value = false },
            properties = DialogProperties(dismissOnClickOutside = false)
        ) {
            Card(
                modifier = Modifier.width(300.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = { dialogOpen.value = false }, modifier = Modifier.align(Alignment.End)) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Close Dialog"
                        )
                    }
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it},
                        label = { Text(labelName) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Spacer(modifier = Modifier.height(4.dp))

                    DropdownInputTemplate(
                        suggestions = suggestions,
                        selectedElement = selectedElement,
                        getStringValue = getStringValue,
                        onValueChange = onValueChange,
                        label = labelType
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            onClick(name)
                            name = ""
                            dialogOpen.value = false
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Добавить")
                    }
                }
            }
        }
    }
}