package com.example.ecommerceapp.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R

val Roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_bold, FontWeight.Bold)
)
val DisplayLarge = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 48.sp
)
val DisplaySmall = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 28.sp
)
// Headline
val HeadlineLarge = TextStyle(
fontFamily = Roboto,
fontWeight = FontWeight.Normal,
fontSize = 32.sp
)
val HeadlineSmall = TextStyle(
fontFamily = Roboto,
fontWeight = FontWeight.Normal,
fontSize = 24.sp
)
// Title
val TitleLarge = TextStyle(
fontFamily = Roboto,
fontWeight = FontWeight.Bold,
fontSize = 20.sp
).copy(letterSpacing = 0.15.sp) // Добавляем капс
val TitleSmall = TextStyle(
fontFamily = Roboto,
fontWeight = FontWeight.Normal,
fontSize = 18.sp
)
// Label
val LabelLarge = TextStyle(
fontFamily = Roboto,
fontWeight = FontWeight.Normal,
fontSize = 16.sp
)
// Body
val BodyLarge = TextStyle(
fontFamily = Roboto,
fontWeight = FontWeight.Normal,
fontSize = 14.sp
)
val BodySmall = TextStyle(
fontFamily = Roboto,
fontWeight = FontWeight.Normal,
fontSize = 12.sp
)

// Set of Material typography styles to start with
val Typography = Typography(
    // Display
    displayLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),


)

@Preview(showBackground = true)
@Composable
fun showTextStyles(){
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Display Small",
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Headline Large",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Headline Small",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Title Small",
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Label Large",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Body Large",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Body Small",
            style = MaterialTheme.typography.bodySmall
        )
    }

}