package com.example.ecommerceapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Card
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ecommerceapp.MainViewModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.models.ProductItem
import com.example.ecommerceapp.ui.theme.GrayCl
import com.example.ecommerceapp.ui.theme.LabelLarge
import com.example.ecommerceapp.ui.theme.HeadlineLarge
import com.example.ecommerceapp.ui.theme.HeadlineSmall
import com.example.ecommerceapp.ui.theme.LightGrayCl
import com.example.ecommerceapp.ui.theme.TitleLarge
import com.example.ecommerceapp.utils.Constants
import com.example.ecommerceapp.utils.Constants.CART_SCREEN
import com.example.ecommerceapp.utils.Constants.MAIN_SCREEN

@Composable
fun CartScreen(navController: NavController, vm: MainViewModel){
    LaunchedEffect(Unit) {
        vm.getAllCartItem()
    }

    val listCartItem = vm.listCartItem.value



    Box(modifier = Modifier.fillMaxSize()) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightGrayCl)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                androidx.compose.material3.Icon(
                    painterResource(id = R.drawable.ic_arrow_back),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                                   navController.navigate(MAIN_SCREEN){
                                       popUpTo(CART_SCREEN)
                                   }
                        },
                    contentDescription = "",

                )


                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .size(width = 100.dp, height = 50.dp)
                        .weight(1f),
                    contentAlignment = Alignment.TopCenter,
                    ) {
                    androidx.compose.material3.Icon(
                        painterResource(id = R.drawable.app_logo),
                        modifier = Modifier,
                        contentDescription = "",
                    )
                }
            }
            Text(text = "Cart", style = HeadlineLarge)
            if (!listCartItem.isEmpty()) {
                CartList(listCartItem, navController, vm)

            } else {
                EmptyList()
            }


        }
        BoxButton(listCartItem.isEmpty(), vm, navController)
    }

}

@Composable
fun BoxButton(isEmptyList: Boolean, vm: MainViewModel, navController: NavController) {
    

    val btnText : String
    val btnColor: Color
    val textColor: Color
    val tint: Color
    val iconId: Int

    if (isEmptyList){

        btnText = "Add something"
        btnColor = Color.White
        textColor = Color.Black
        tint = Color.Black
        iconId = R.drawable.ic_basket_empty
    }else{

        btnText = "Buy"
        btnColor = Color.Black
        textColor = Color.White
        tint = Color.White
        iconId = R.drawable.ic_basket_fill

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        contentAlignment = Alignment.BottomCenter,

        ){
        FloatingActionButton(
            backgroundColor = btnColor,
            onClick = {
                if (isEmptyList){
                    navController.navigate(MAIN_SCREEN){
                        popUpTo(CART_SCREEN)
                    }
                }else{
                    vm.deleteALLCartItems()
                }
                      },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),

        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = btnText,
                    style = TitleLarge,
                    textAlign = TextAlign.Start,
                    color = textColor
                )
                Icon(
                    painter = painterResource(id = iconId),
                    tint = tint,
                    contentDescription = null
                )
            }
        }
    }
    

}

@Composable
fun CartList(listCartItem: List<ProductItem>, navController: NavController, vm : MainViewModel) {



    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(listCartItem) { cartItem ->
            CartItemRow(cartItem)
        }
    }
}

@Composable
fun CartItemRow(cartItem: ProductItem) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                 // Делаем высоту Row минимальной для наибольшего из дочерних элементов
                .padding(8.dp)
        ) {
            // Контейнер для изображения

                AsyncImage(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.55f)
                        .padding(8.dp),
                    model = ImageRequest.Builder(LocalContext.current).data(cartItem.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )


                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.45f)
                        .padding(8.dp)
                        .border(2.dp, Color.Black),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {


                    Text(text = cartItem.title, style = LabelLarge, textAlign = TextAlign.Start)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically // центрируем элементы по вертикали
                    ) {
                        // Тексты вплотную друг к другу
                        Text(
                            text = cartItem.price,
                            style = HeadlineSmall,
                            textAlign = TextAlign.Start,
                            // добавляем небольшой отступ между текстами
                        )
                        Text(
                            text = "$",
                            style = HeadlineSmall,
                            textAlign = TextAlign.Start,
                            color = GrayCl
                        )

                        // Spacer, чтобы отодвинуть иконку вправо
                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            painter = painterResource(id = R.drawable.ic_cart_remove),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp) // установите нужный размер иконки
                        )
                    }
                }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevItem(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .height(IntrinsicSize.Min) // Делаем высоту Row минимальной для наибольшего из дочерних элементов
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.45f)
            ){
            Text(
                text = "Первая часть dsfdsfsdfsdfdsfsfsf" +
                        "shkdfjhskdfhskdjfhsdf" +
                        "dfhsjdfhkjhdfksdhfsdfksfshfhdfshkfjs" +
                        "sfskdfhsdkafhlakjhfkadshflafkadjhfsa" +
                        "fkdshfdskjfhdslfhdjfhsdhfskdfhsdhf''dsfhdskfjdsh;fhsdkfh" +
                        "sfhdsjfhsdkfhkshkhfhsdf" +
                        "sdfhskdjfhdskhfkdshfksdhf" +
                        "hdsfkjshfdskfhdskfhadsfhkadshffkjladfjkdfkadslfjhalfk ", style = LabelLarge, textAlign = TextAlign.Start,
                modifier = Modifier
                    .border(2.dp, Color.Black)
            )
            }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.55f)
                        .padding(8.dp)
                        .border(2.dp, Color.Black),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {


                    Text(text = "cartItem.title", style = LabelLarge, textAlign = TextAlign.Start)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically // центрируем элементы по вертикали
                    ) {
                        // Тексты вплотную друг к другу
                        Text(
                            text = "cartItem.price",
                            style = HeadlineSmall,
                            textAlign = TextAlign.Start,
                            // добавляем небольшой отступ между текстами
                        )
                    }
                }

        }
    }

}
@Composable
fun EmptyList(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("Empty")
    }
}