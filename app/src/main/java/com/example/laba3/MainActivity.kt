package com.example.laba3

import android.R.attr.start
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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
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
import androidx.compose.ui.text.TextStyle
import kotlin.math.round
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.derivedStateOf



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
        .width(200.dp)
        .height(56.dp)



    Column(modifier = modifier){
        //Сумма заказа
        var summ = remember { mutableStateOf("") }
        Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically){
            TextCell("Сумма заказа:")

            TextField(value = summ.value, modifier=TextFieldModifier, onValueChange = {
                newText -> summ.value=newText}, enabled = true, textStyle = TextStyle(fontSize = 18.sp))
        }
        //Количество блюд
        var numberofdishes = remember { mutableStateOf("") }
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            TextCell("Количество блюд:")
            TextField(value = numberofdishes.value, modifier=TextFieldModifier,  onValueChange = {
                newText -> numberofdishes.value = newText
            }, enabled = true)
        }
        //Slider
            var sliderPosition by remember { mutableFloatStateOf(25f) }

            Row(modifier = Modifier.fillMaxWidth().padding(top=200.dp)){
                TextCell("Чаевые:")
                Text(text="${sliderPosition.toInt()}%", fontSize = 16.sp, modifier=Modifier.padding(all = 4.dp))
            }

            Row(modifier = Modifier.fillMaxWidth()){

                Slider(
                    modifier = Modifier.padding(10.dp),
                    value = sliderPosition,
                    valueRange = 0f..25f,
                    onValueChange = {sliderPosition = it},
                    steps = 24
                )
            }

        Row(modifier = Modifier.fillMaxWidth()){

           // val (selectedOption, onOptionSelected) = remember { mutableStateOf(sails[0]) }
            val sails = listOf("3", "5", "7", "10")

            var selectedOption by remember(numberofdishes.value) {
                mutableStateOf(
                    try {
                        val count = numberofdishes.value.toInt()
                        when (count) {
                            in 1..2 -> sails[0]
                            in 3..5 -> sails[1]
                            in 6..10 -> sails[2]
                            else -> sails[3]
                        }
                    } catch (e: NumberFormatException) {
                        sails[3]
                    }
                )
            }



                Column (verticalArrangement = Arrangement.Center, modifier = Modifier.padding(30.dp), horizontalAlignment = Alignment.Start){

                Text(text = "Скидка: ${selectedOption}%", fontSize = 16.sp)
            }


            sails.forEach { text ->
                Column(modifier = Modifier.selectableGroup()) {


                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = {text == selectedOption}
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                        Text( text = "${text}%", fontSize = 16.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(start = 15.dp))
                    }




                }
            }

        }
    }

}


@Composable
fun TextCell(text: String, modifier: Modifier=Modifier){
    val Textmodifier = modifier
        .padding(all = 4.dp)


    Text(text = text, modifier=Textmodifier, textAlign = TextAlign.Center, fontSize = 16.sp)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laba3Theme {
        MainScreen()
    }
}