package th.learn.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import th.learn.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember {mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember {  mutableStateOf("Meters")}
    var isExpandedInputUnit by remember { mutableStateOf(false)}
    var isExpandedOutputUnit by remember { mutableStateOf(false)}
    val inputConversionFactor = remember { mutableDoubleStateOf(1.00) }
    val outputConversionFactor = remember {mutableDoubleStateOf(1.00)}

    val titleTextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace,
        fontSize = 24.sp,
        color = Color.Red
    )

    val resultTextStyle = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontFamily = FontFamily.Default,
        fontSize = 24.sp,
        color = Color.DarkGray
    )

    fun convertUnit(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * inputConversionFactor.doubleValue * 100.0
                /outputConversionFactor.doubleValue ).roundToInt() /
                100.0
        outputValue = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(
            text = "Unit Converter",
            style = titleTextStyle
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {inputValue = it},
            label = {Text(text = "Enter Value")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { isExpandedInputUnit = true}) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,"Arrow Down")
                }
                DropdownMenu(expanded = isExpandedInputUnit, onDismissRequest = {
                    isExpandedInputUnit = false}){
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            isExpandedInputUnit = false
                            inputUnit = "Centimeters"
                            inputConversionFactor.doubleValue = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            isExpandedInputUnit = false
                            inputUnit = "Meters"
                            inputConversionFactor.doubleValue = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            isExpandedInputUnit = false
                            inputUnit = "Feet"
                            inputConversionFactor.doubleValue = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            isExpandedInputUnit = false
                            inputUnit = "Millimeters"
                            inputConversionFactor.doubleValue = 0.001
                            convertUnit()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { isExpandedOutputUnit = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,"Arrow Down")
                }
                DropdownMenu(expanded = isExpandedOutputUnit, onDismissRequest = {
                    isExpandedOutputUnit = false
                }){
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            isExpandedOutputUnit = false
                            outputUnit = "Centimeters"
                            outputConversionFactor.doubleValue = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            isExpandedOutputUnit = false
                            outputUnit = "Meters"
                            outputConversionFactor.doubleValue = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            isExpandedOutputUnit = false
                            outputUnit = "Feet"
                            outputConversionFactor.doubleValue = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            isExpandedOutputUnit = false
                            outputUnit = "Millimeters"
                            outputConversionFactor.doubleValue = 0.001
                            convertUnit()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Result: $outputValue $outputUnit",
            style = resultTextStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
