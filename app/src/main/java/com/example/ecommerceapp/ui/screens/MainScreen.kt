package com.example.ecommerceapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import com.example.ecommerceapp.utils.Constants.CART_SCREEN
import androidx.compose.material3.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ecommerceapp.MainViewModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.models.ProductItem
import com.example.ecommerceapp.ui.theme.AverageGray
import com.example.ecommerceapp.ui.theme.BodyLarge
import com.example.ecommerceapp.ui.theme.BodySmall
import com.example.ecommerceapp.ui.theme.DarkGrayCl
import com.example.ecommerceapp.ui.theme.DisplaySmall
import com.example.ecommerceapp.ui.theme.GrayCl
import com.example.ecommerceapp.ui.theme.HeadlineLarge
import com.example.ecommerceapp.ui.theme.HeadlineSmall
import com.example.ecommerceapp.ui.theme.LabelLarge
import com.example.ecommerceapp.ui.theme.LightGrayCl
import com.example.ecommerceapp.ui.theme.TitleLarge
import com.example.ecommerceapp.ui.theme.TitleSmall
import com.example.ecommerceapp.ui.theme.WhiteCl
import com.example.ecommerceapp.utils.Constants.MAIN_SCREEN
import kotlinx.coroutines.launch


@Composable
fun MainScreen(navController: NavHostController, vm: MainViewModel) {


    val listProducts = vm.listProducts.value

    var showDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("All") }

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
                painterResource(id = R.drawable.vector_category_fill),
                modifier = Modifier
                    .size(24.dp)
                    .clickable {

                        showDialog = true

                    },
                contentDescription = "",
            )
            if (showDialog) {
                val listCategories =
                    listOf("All", "Men's clothing", "Jewelry", "Electronics", "Women's clothing")
                SelectionDialog(
                    items = listCategories,
                    onItemSelected = { item ->
                        selectedItem = item
                        if (!selectedItem.equals("All", ignoreCase = true)){
                            vm.getProductsByCategory(selectedItem)
                        }

                    },
                    onDismissRequest = {
                        showDialog = false
                    }
                )
            }




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
            androidx.compose.material3.Icon(
                painterResource(id = R.drawable.vector_shop_cart_fill),
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.navigate(CART_SCREEN) {
                            popUpTo(MAIN_SCREEN)
                        }
                    },
                contentDescription = "",
            )

        }
        Text(text = "Products", style = HeadlineLarge)
        DynamicHeightGrid(imageItems = listProducts, vm = vm, navController)
    }
}

@Composable
fun SelectionDialog(
    items: List<String>,
    onItemSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    var selectedOption by remember { mutableStateOf(items[0]) }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties()
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            ) {
            Column(
                modifier = Modifier

                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    androidx.compose.material3.Icon(
                        painterResource(id = R.drawable.vector_category_fill),
                        modifier = Modifier
                            .size(30.dp)
                            .padding(4.dp),
                        contentDescription = "",
                    )
                    Text(text = "Categories", style = HeadlineSmall, textAlign = TextAlign.Center, modifier = Modifier.padding(4.dp))
                    Text(text = "Choose your preferred", style = BodyLarge, textAlign = TextAlign.Center, color = DarkGrayCl, modifier = Modifier.padding(4.dp))

                    
                }

                Spacer(modifier = Modifier.height(8.dp))

                items.forEach { item ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp, horizontal = 16.dp)
                            .clickable {
                                selectedOption = item
                            }
                    ) {

                        Text(text = item, style = LabelLarge)

                        RadioButton(
                            selected = selectedOption == item,
                            onClick = { selectedOption = item }
                        )

                    }
                    Divider(thickness = 1.dp, color = AverageGray, modifier = Modifier.fillMaxWidth())
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(16.dp)
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Decline", style = LabelLarge)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(
                        onClick = {
                            onItemSelected(selectedOption)
                            onDismissRequest()
                        }
                    ) {
                        Text("Accept", style = LabelLarge)
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DynamicHeightGrid(
    imageItems: List<ProductItem>,
    vm: MainViewModel,
    navController: NavHostController
) {
    val stateStaggered = rememberLazyStaggeredGridState()
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val selectedProduct = remember { mutableStateOf<ProductItem?>(null) }
    val isProductInCartState = vm.isProductInCartState





    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            selectedProduct.value?.let {
                vm.isProductInCart(it.id)
                BottomSheetItemContent(
                    product = it,
                    isProductInCart = isProductInCartState,
                    vm = vm,
                    navController = navController
                )
            }
        },
        sheetShape = RoundedCornerShape(8.dp)
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            state = stateStaggered,
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalItemSpacing = 4.dp,
        ) {
            items(imageItems) { item ->

                DynamicHeightGridItem(item = item) {

                    // Показываем нижний лист сразу при загрузке экрана

                    selectedProduct.value = item
                    coroutineScope.launch {
                        modalBottomSheetState.show()
                    }
                }
            }
        }
    }


}

@Composable
fun BottomSheetItemContent(
    product: ProductItem,
    isProductInCart: MutableState<Boolean>,
    vm: MainViewModel,
    navController: NavHostController
) {
    val textBtn: String
    val colorBtn: Color
    val textColor: Color
    val iconId: Int
    val iconColor: Color

    if (isProductInCart.value) {
        textBtn = "Go to cart"
        colorBtn = Color.Black
        textColor = Color.White
        iconId = R.drawable.ic_cart_go
        iconColor = Color.White
    } else {
        textBtn = "Add to cart"
        colorBtn = Color.White
        textColor = Color.Black
        iconId = R.drawable.ic_cart_add
        iconColor = Color.Black
    }

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(product.image).crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
        )
        Divider(thickness = 1.dp, color = AverageGray, modifier = Modifier.fillMaxWidth())
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = product.title, style = TitleSmall, modifier = Modifier.padding(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(text = "Category", style = LabelLarge, color = GrayCl)
                Text(
                    text = product.category,
                    style = LabelLarge,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(text = "Rating", style = LabelLarge, color = GrayCl)
                for (i in 1..5) {
                    androidx.compose.material3.Icon(
                        painterResource(id = R.drawable.ic_star_fill),
                        modifier = Modifier
                            .size(24.dp)
                            .padding(4.dp),
                        contentDescription = "",
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(text = "Price", style = LabelLarge, color = GrayCl)
                Text(
                    text = product.price,
                    style = DisplaySmall,
                    modifier = Modifier.padding(start = 4.dp)
                )
                Text(
                    text = "$",
                    style = DisplaySmall,
                    modifier = Modifier.padding(),
                    color = GrayCl
                )

            }

            Text(
                text = "Description",
                style = LabelLarge,
                color = GrayCl,
                modifier = Modifier.padding(start = 4.dp)
            )
            Text(
                text = product.description,
                style = BodySmall,
                color = DarkGrayCl,
                modifier = Modifier.padding(start = 4.dp)
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Black),
                contentPadding = PaddingValues(16.dp),
                onClick =
                {
                    if (isProductInCart.value) {
                        navController.navigate(CART_SCREEN) {
                            popUpTo(MAIN_SCREEN)
                        }

                    } else {
                        vm.insertCartItem(product)
                    }
                },
                colors = ButtonDefaults.buttonColors(colorBtn)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = textBtn,
                        style = TitleLarge,
                        textAlign = TextAlign.Start,
                        color = textColor
                    )
                    Icon(
                        painter = painterResource(id = iconId),
                        tint = iconColor,
                        contentDescription = null
                    )
                }
            }
        }
    }
}


@Composable
fun DynamicHeightGridItem(item: ProductItem, onClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(WhiteCl)
            .clickable { onClick() }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(item.image).crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()

            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
            ) {

                Text(
                    text = item.title,
                    style = BodySmall,
                    maxLines = 2,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(0.6f)
                )
                Text(
                    text = item.price,
                    style = LabelLarge,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(0.4f)
                )
                Text(
                    text = "$",
                    style = LabelLarge,
                    textAlign = TextAlign.End,
                    color = GrayCl
                )

            }
        }
    }
}
