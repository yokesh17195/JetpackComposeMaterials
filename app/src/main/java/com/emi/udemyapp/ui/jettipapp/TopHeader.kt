package com.emi.udemyapp.ui.jettipapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopHeader(totalPerPerson: MutableState<Int>) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(140.dp),
        color = Color(0x51592ED0),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text ="Total Per Person",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text ="$${totalPerPerson.value}",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopHeaderPreview(){
    var total = remember {mutableStateOf(0) }
    TopHeader(total)
}