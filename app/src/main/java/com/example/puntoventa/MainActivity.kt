package com.example.puntoventa

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.puntoventa.ui.theme.PuntoVentaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PuntoVentaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PuntoDeVentaScreen()
                }
            }
        }
    }
}

@Composable
fun PuntoDeVentaScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Bar La Peña",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Creado por: Javier Garrido",
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        val bebidas = listOf(
            "Cervezas" to 1.40,
            "Refrescos" to 1.40,
            "Cafés" to 1.20,
            "Infusiones" to 1.20,
            "Copas Anís" to 1.40,
            "Copas coñac" to 1.40,
            "Chapita Whisky" to 2.50,
            "Copas Martini" to 2.50,
            "Copas Rioja" to 1.40,
            "Botellines" to 1.40,
            "Batidos" to 1.40,
            "Zumos" to 1.40,
            "Cola Cao" to 1.40,
            "Mosto" to 0.80,
            "Tinto" to 1.40,
            "Botella Agua 0,5" to 0.60,
            "Botella Agua 1L" to 1.20,
            "Litronas" to 2.20,
            "Coca Cola 2L" to 2.50,
            "Fanta 2L" to 2.50,
            "Cubalibre Ginebra" to 5.00,
            "Cubalibre Whisky" to 5.00,
            "Cubalibre Ron" to 4.50
        ).sortedBy { it.second }

        val comidas = listOf(
            "Lomito Cerdo" to 2.80,
            "Lomito Pollo" to 2.80,
            "Pinchito Pollo" to 2.80,
            "Serranito de Cerdo" to 6.00,
            "Serranito de Pollo" to 6.00,
            "Pechuga de Pollo" to 6.00,
            "Churrasco" to 7.00,
            "Pez Espada" to 8.00,
            "Chocos Media" to 8.00,
            "Calamares Media" to 7.00,
            "Chipirones Plancha Media" to 7.00,
            "Chipirones Fritos Media" to 7.00,
            "Adobos Media" to 7.00,
            "Chocos Entera" to 10.00,
            "Calamares Entera" to 9.00,
            "Chipirones Plancha Entera" to 9.00,
            "Chipirones Fritos Entera" to 9.00,
            "Adobos Entera" to 9.00,
            "Bacalao con aceite Tapa" to 2.80,
            "Bacalao con aceite Media" to 7.00,
            "Bacalao con aceite Racion" to 9.00,
            "Chorizo Tapa" to 2.80,
            "Chorizo Media" to 7.00,
            "Chorizo Racion" to 9.00,
            "Ensaladilla Tapa" to 2.80,
            "Ensaladilla Media" to 7.00,
            "Ensaladilla Racion" to 9.00,
            "Salchichón Tapa" to 2.80,
            "Salchichón Media" to 7.00,
            "Salchichón Racion" to 9.00,
            "Langostinos Tapa" to 2.80,
            "Langostinos Media" to 7.00,
            "Langostinos Racion" to 9.00,
            "Jamón Tapa" to 2.80,
            "Jamón Media" to 7.00,
            "Jamón Racion" to 9.00,
            "Queso Tapa" to 2.80,
            "Queso Media" to 7.00,
            "Queso Racion" to 9.00
        ).sortedBy { it.second }

        val otros = listOf(
            "Viena" to 0.40,
            "Bollo" to 0.50,
            "Picaito" to 0.50,
            "Hielos" to 1.40,
            "Regaña" to 1.50,
            "Picos" to 1.00
        ).sortedBy { it.second }

        val cantidadesBebidas = remember { mutableStateListOf(*Array(bebidas.size) { 0 }) }
        val cantidadesComidas = remember { mutableStateListOf(*Array(comidas.size) { 0 }) }
        val cantidadesOtros = remember { mutableStateListOf(*Array(otros.size) { 0 }) }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                SectionTitle(title = "Bebidas")
            }
            items(bebidas.size) { index ->
                ProductoRow(bebidas[index].first, bebidas[index].second, cantidadesBebidas, index)
            }
            item {
                SectionTitle(title = "Comidas")
            }
            items(comidas.size) { index ->
                ProductoRow(comidas[index].first, comidas[index].second, cantidadesComidas, index)
            }
            item {
                SectionTitle(title = "Otros")
            }
            items(otros.size) { index ->
                ProductoRow(otros[index].first, otros[index].second, cantidadesOtros, index)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                for (i in cantidadesBebidas.indices) {
                    cantidadesBebidas[i] = 0
                }
                for (i in cantidadesComidas.indices) {
                    cantidadesComidas[i] = 0
                }
                for (i in cantidadesOtros.indices) {
                    cantidadesOtros[i] = 0
                }
            }) {
                Text(text = "Limpiar")
            }

            Button(onClick = {
                val intent = Intent(context, ResumenActivity::class.java).apply {
                    putStringArrayListExtra("bebidas", ArrayList(bebidas.map { it.first }))
                    putIntegerArrayListExtra("cantidadesBebidas", ArrayList(cantidadesBebidas))
                    putStringArrayListExtra("comidas", ArrayList(comidas.map { it.first }))
                    putIntegerArrayListExtra("cantidadesComidas", ArrayList(cantidadesComidas))
                    putStringArrayListExtra("otros", ArrayList(otros.map { it.first }))
                    putIntegerArrayListExtra("cantidadesOtros", ArrayList(cantidadesOtros))
                }
                context.startActivity(intent)
            }) {
                Text(text = "Pagar")
            }
        }
    }
}

@Composable
fun ProductoRow(nombre: String, precio: Double, cantidades: MutableList<Int>, index: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                cantidades[index]++
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$nombre (€${precio.format(2)})",
            fontSize = 18.sp // Aumento del tamaño de la fuente
        )

        Text(
            text = if (cantidades[index] > 0) "x ${cantidades[index]}" else "",
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 18.sp // Aumento del tamaño de la fuente
        )
    }
}


fun Double.format(digits: Int) = "%.${digits}f".format(this)
