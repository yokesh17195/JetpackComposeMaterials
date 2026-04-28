package com.emi.udemyapp.ui.jettipapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun BillCalculator(totalPerPerson: MutableState<Int>) {

    var billAmount = remember { mutableStateOf("") }
    var split = remember { mutableStateOf(0) }
    var percentage = remember { mutableStateOf(0f) }
    var tipAmount = remember { mutableStateOf("0") }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp),
        color = Color.White,
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 8.dp
    ) {
        Column {
            BillInput(billAmount){ input ->
                billAmount.value = input.filter { it.isDigit() }.toString()
                calculateBillAmount(billAmount,split,percentage,totalPerPerson,tipAmount)
            }
            Spacer(modifier = Modifier.height(10.dp))
            SplitView(split){ isAdd ->
                if (isAdd) {
                    split.value += 1
                } else {
                    if (split.value > 0) {
                        split.value -= 1
                    }
                }
                calculateBillAmount(billAmount, split, percentage, totalPerPerson, tipAmount)
            }
            Spacer(modifier = Modifier.height(10.dp))
            TipView(tipAmount)
            Spacer(modifier = Modifier.height(10.dp))
            TipPercentageView(percentage)
            Spacer(modifier = Modifier.height(10.dp))
            PercentageSlider(percentage){
                percentage.value = it
                calculateBillAmount(billAmount, split, percentage, totalPerPerson, tipAmount)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun BillInput(billAmount: MutableState<String>, valueChange: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var inputValue by remember { mutableStateOf("") }

    OutlinedTextField(
        value = billAmount.value,
        onValueChange = valueChange,
        label = { Text("Enter Bill") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Default.AttachMoney, contentDescription = "Money Icon")
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide() // hide keyboard
                // your action here (submit, validate, etc.)
            }
        )
    )
}

@Composable
fun SplitIcon(
    imageVector: ImageVector,
    onClick: ()-> Unit
) {
    Box(
        modifier = Modifier
            .size(30.dp)
            .shadow(
                8.dp,
                shape = CircleShape,
                clip = true
            )
            .background(Color.White)
            .clickable(onClick = onClick)
        ,
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = imageVector,
            contentDescription = "",
            tint = Color.Black)
    }
}

@Composable
fun SplitView(split: MutableState<Int>,onClick: (isAdd: Boolean)-> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Split",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp)
        )
        Row(
            modifier = Modifier.padding(end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SplitIcon(Icons.Default.Remove){
                onClick(false)
            }
            Text(
                color = Color.Black,
                text = split.value.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp,end = 10.dp)
            )
            SplitIcon(Icons.Default.Add){
                onClick(true)
            }
        }
    }
}

@Composable
fun TipView(tipAmount: MutableState<String>) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(end = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = "Tip",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp)
        )
        Text(
            text = "$${tipAmount.value}",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun TipPercentageView(percentage: MutableState<Float>) {
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "${percentage.value.roundToInt()}%",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PercentageSlider(percentage: MutableState<Float>,onValueChange:(Float)-> Unit) {

    val steps = 4

    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center ){
        Slider(
            value = percentage.value,
            onValueChange = onValueChange,
            valueRange = 0f..100f,
            thumb = {
                Box(
                    modifier = Modifier
                        .size(24.dp) // control size
                        .background(MaterialTheme.colorScheme.surface, shape = CircleShape)
                )
            },
            track = { sliderPositions ->
                val fraction = (percentage.value / 100f).coerceIn(0f, 1f)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(Color.LightGray, RoundedCornerShape(50))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction)   // ✅ only this controls width
                            .fillMaxHeight()          // ✅ keep height same
                            .background(Color.Blue, RoundedCornerShape(50))
                    )
                    // 🔸 Split markers
                    Row(
                        modifier = Modifier.matchParentSize(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        repeat(steps + 1) {
                            Box(
                                modifier = Modifier
                                    .width(2.dp)
                                    .fillMaxHeight()
                                    .background(Color.Black)
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BillCalculatorPreview() {
    var totalPerPerson = remember { mutableStateOf(0) }
    BillCalculator(totalPerPerson)
}

private fun calculateBillAmount(
    billAmount: MutableState<String>,
    split: MutableState<Int>,
    percentage: MutableState<Float>,
    totalPerPerson1: MutableState<Int>,
    tipAmount: MutableState<String>
) {
    if(billAmount.value.isEmpty()){
        return
    }
    var tipsAmount = 0.0
    var totalAmountWithTips = 0.0
    var totalPerPerson =0.0
    tipsAmount = (billAmount.value.toDouble() * percentage.value)/100
    tipAmount.value = tipsAmount.roundToInt().toString()
    Log.e("BillAmount", "calculateBillAmount: timsAMount $tipsAmount", )
    totalAmountWithTips = tipsAmount + billAmount.value.toDouble()
    Log.e("BillAmount", "calculateBillAmount: totalAmountWithTips $totalAmountWithTips", )
    if(split.value>0){
        totalPerPerson = totalAmountWithTips/split.value
    }
    Log.e("BillAmount", "calculateBillAmount: totalPerPerson $totalPerPerson", )
    totalPerPerson1.value = totalPerPerson.roundToInt()
}