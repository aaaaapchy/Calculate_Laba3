package com.example.laba3

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.laba3.ui.theme.Laba3Theme
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Slider
import androidx.compose.material3.TextField

import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFF6200EE),
                    background = Color.White,
                    surface = Color.White
                    )
            ){
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),

                    content = { innerPadding ->
                        MainScreen(Modifier.padding(innerPadding))
                    }
                )
            }

        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier){
    val TextFieldModifier = Modifier
        .padding(all = 10.dp)
        .width(150.dp)
        .height(30.dp)

    Column(modifier = modifier){
        val summ = remember { mutableStateOf("") }
        Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically){
            TextCell("Сумма заказа:")
            TextField(value = summ.value, modifier=TextFieldModifier, onValueChange = {
                newText -> summ.value=newText},  )
        }

        val numberofdishes = remember { mutableStateOf("") }
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            TextCell("Количество блюд:")
            TextField(value = numberofdishes.value, modifier=TextFieldModifier,  onValueChange = {
                newText -> numberofdishes.value = newText
            })
        }
        TextCell("Чаевые:", modifier = Modifier.padding(top = 200.dp))
        Row(modifier = Modifier.fillMaxWidth()){
            var sliderPosition by remember { mutableFloatStateOf(25f) }
            Slider(
                modifier = Modifier.padding(10.dp),
                value = sliderPosition,
                valueRange = 1f..25f,
                onValueChange = {},
                steps = 0
            )
        }
        Row(modifier = Modifier.fillMaxWidth()){
            Column (verticalArrangement = Arrangement.Center){
                TextCell("Скидка:")
            }

            Column {
                val state = remember { mutableStateOf(true) }
                Row (modifier = Modifier.padding(start = 10.dp, bottom = 0.dp).fillMaxWidth() ,verticalAlignment = Alignment.CenterVertically){
                    RadioButton(
                        selected = state.value,
                        onClick = { state.value = true }
                    )
                    RadioButton(
                        selected = state.value,
                        onClick = { state.value = true }
                    )
                    RadioButton(
                        selected = state.value,
                        onClick = { state.value = true }
                    )
                    RadioButton(
                        selected = state.value,
                        onClick = { state.value = true }
                    )
                }
                Row(modifier = Modifier.padding(start = 10.dp, bottom = 0.dp, top = 0.dp).fillMaxWidth(),verticalAlignment = Alignment.CenterVertically){
                    Text(text = "3%", modifier= Modifier.padding(10.dp), textAlign = TextAlign.Center, fontSize = 15.sp)
                    Text(text = "5%", modifier= Modifier.padding(10.dp), textAlign = TextAlign.Center, fontSize = 15.sp)
                    Text(text = "7%", modifier= Modifier.padding(10.dp), textAlign = TextAlign.Center, fontSize = 15.sp)
                    Text(text = "10%", modifier= Modifier.padding(10.dp), textAlign = TextAlign.Center, fontSize = 15.sp)
                }
            }
        }
    }

}


@Composable
fun TextCell(text: String, modifier: Modifier=Modifier){
    val Textmodifier = modifier
        .padding(all = 4.dp)


    Text(text = text, modifier=Textmodifier, textAlign = TextAlign.Center, fontSize = 15.sp)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laba3Theme {
        MainScreen()
    }
}