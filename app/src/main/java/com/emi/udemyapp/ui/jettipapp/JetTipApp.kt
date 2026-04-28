package com.emi.udemyapp.ui.jettipapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun JetTipApp(innerPaddingValues: PaddingValues){

    var totalPerPerson = remember { mutableStateOf(0) }

    Surface(modifier = Modifier.padding(innerPaddingValues).fillMaxSize(), color = androidx.compose.ui.graphics.Color(0xFFFFFFFF)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)) {
            TopHeader(totalPerPerson)
            Spacer(modifier = Modifier.height(20.dp))
            BillCalculator(totalPerPerson)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetTipAppPreview(){
    JetTipApp(innerPaddingValues = PaddingValues(1.dp))
}