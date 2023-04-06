package com.example.calculator

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.DarkColorPalette
import com.example.calculator.ui.theme.LightColorPalette

@Composable
fun SimonScreen(navController: NavController){
    var darkTheme by remember {
        mutableStateOf(false)
    }
    var text by remember {
        mutableStateOf("")
    }
    var icon by remember {
        mutableStateOf(R.drawable.moon)
    }
    if (darkTheme){
        text = "Light Mode"
        icon = R.drawable.sun
    }else{
        text = "Dark Mode"
        icon = R.drawable.moon
    }

    CalculatorTheme(darkTheme = darkTheme) {
        Scaffold(
            topBar = {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.clickable { navController.popBackStack() })
                    Row(modifier = Modifier
                        .border(
                            border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.onBackground),
                            shape = RoundedCornerShape(15.dp)
                        )){
                        Row(modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                darkTheme = !darkTheme

                            }) {
                            Text(text = text)
                            Spacer(modifier = Modifier.width(5.dp))
                            Icon(painter = painterResource(id = icon), contentDescription = null, modifier = Modifier.size(20.dp))
                        }

                    }
                }
            },
            bottomBar = { Credit()}
        ) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .padding(it), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Body()
            }

        }
    }

}

@Preview
@Composable
private fun PreviewSimonScreen() {
    SimonScreen(rememberNavController())
}

@Composable
fun Credit() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Text(text = "credit: Šimon Ryba 1.IT", fontWeight = FontWeight.Bold)
    }

}

@Composable
fun Body(
    modifier: Modifier = Modifier,

) {
    var people by remember {
        mutableStateOf(0)
    }
    var saves by remember {
        mutableStateOf("")
    }

        Column(modifier = modifier
            .fillMaxHeight(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Let's count people!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
                Text(text = "People: $people")
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = {people++}, modifier = Modifier.size(height = 45.dp, width = 130.dp),colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background), shape = RoundedCornerShape(20.dp),border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onBackground)) {
                        Text(text = "Increment")
                    }
                    Button(onClick = {
                        if (people > 0) {
                            people--
                        }
                                     }, modifier = Modifier.size(height = 45.dp, width = 130.dp),colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background), shape = RoundedCornerShape(20.dp),border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onBackground)) {
                        Text(text = "Decrement")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = {saves = "$saves$people - " }, modifier = Modifier.size(height = 45.dp, width = 130.dp),colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background), shape = RoundedCornerShape(20.dp),border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onBackground)) {
                        Text(text = "Save")
                    }
                    Button(onClick = {people = 0}, modifier = Modifier.size(height = 45.dp, width = 130.dp),colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background), shape = RoundedCornerShape(20.dp),border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onBackground)) {
                        Text(text = "Reset People")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {saves = ""}, modifier = Modifier.size(height = 45.dp, width = 130.dp),colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background), shape = RoundedCornerShape(20.dp),border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onBackground)) {
                    Text(text = "Reset Saves")
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
                Text(text = "Last save/s: $saves")
            }





}