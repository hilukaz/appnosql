package com.example.appnosql

import DBHandler
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appnosql.ui.theme.AppNoSqlTheme
import com.example.appnosql.ui.theme.PurpleGrey40


class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNoSqlTheme {
                // on below line we are specifying background color for our application
                Surface(
                    // on below line we are specifying modifier and color for our app
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                    // on the below line we are specifying the theme as the scaffold.
                    Scaffold(

                        // in scaffold we are specifying the top bar.
                        topBar = {

                            // inside top bar we are specifying background color.
                            TopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),

                                // along with that we are specifying title for our top bar.
                                title = {

                                    // in the top bar we are specifying tile as a text
                                    Text(
                                        // on below line we are specifying
                                        // text to display in top app bar.
                                        text = "GFG",

                                        // on below line we are specifying
                                        // modifier to fill max width.
                                        modifier = Modifier.fillMaxWidth(),

                                        // on below line we are specifying
                                        // text alignment.
                                        textAlign = TextAlign.Center,

                                        // on below line we are specifying
                                        // color for our text.
                                        color = Color.White
                                    )
                                })
                        }) {
                        // on below line we are calling our method to display UI
                        addDataToDatabase(LocalContext.current)
                    }
                }
            }
        }
    }
}

// on below line we are creating battery status function.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addDataToDatabase(
    context: Context
) {

    val activity = context as Activity
    // on below line creating a variable for battery status
    val courseName = remember {
        mutableStateOf(TextFieldValue())
    }
    val courseDuration = remember {
        mutableStateOf(TextFieldValue())
    }
    val courseTracks = remember {
        mutableStateOf(TextFieldValue())
    }
    val courseDescription = remember {
        mutableStateOf(TextFieldValue())
    }


    // on below line we are creating a column,
    Column(
        // on below line we are adding a modifier to it,
        modifier = Modifier
            .fillMaxSize()
            // on below line we are adding a padding.
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        var dbHandler: DBHandler = DBHandler(context)

        Text(
            text = "SQlite Database in Android",
            color = PurpleGrey40,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        SimpleOutlinedTextFieldSample(
            label = "Enter your course name",
            textValue = courseName.value,
            onValueChange = { courseName.value = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        SimpleOutlinedTextFieldSample(
            label = "Enter your course duration",
            textValue = courseDuration.value,
            onValueChange = { courseDuration.value = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        SimpleOutlinedTextFieldSample(
            label = "Enter your course tracks",
            textValue = courseTracks.value,
            onValueChange = { courseTracks.value = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        SimpleOutlinedTextFieldSample(
            label = "Enter your course description",
            textValue = courseDescription.value,
            onValueChange = { courseDescription.value = it }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = {
            dbHandler.addNewCourse(
                courseName.value.text,
                courseDuration.value.text,
                courseDescription.value.text,
                courseTracks.value.text
            )
            Toast.makeText(context, "Course Added to Database", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Add Course to Database", color = Color.White)
        }

        // on below line we are adding spacer
        Spacer(modifier = Modifier.height(15.dp))

        // on below line creating a button to open view course activity
        Button(onClick = {
            val i = Intent(context, ViewCourses::class.java)
            context.startActivity(i)
        }) {
            // on below line adding a text for our button.
            Text(text = "Read Courses to Database", color = Color.White)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldSample(label: String, textValue: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {
    OutlinedTextField(
        value = textValue,
        onValueChange = { onValueChange(it) },
        label = { Text(label) }
    )
}