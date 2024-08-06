package com.example.puntoventa

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.puntoventa.ui.theme.PuntoVentaTheme

class ResumenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bebidas = intent.getStringArrayListExtra("bebidas") ?: arrayListOf()
        val cantidadesBebidas = intent.getIntegerArrayListExtra("cantidadesBebidas") ?: arrayListOf()
        val comidas = intent.getStringArrayListExtra("comidas") ?: arrayListOf()
        val cantidadesComidas = intent.getIntegerArrayListExtra("cantidadesComidas") ?: arrayListOf()
        val otros = intent.getStringArrayListExtra("otros") ?: arrayListOf()
        val cantidadesOtros = intent.getIntegerArrayListExtra("cantidadesOtros") ?: arrayListOf()

        setContent {
            PuntoVentaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ResumenScreen(bebidas, cantidadesBebidas, comidas, cantidadesComidas, otros, cantidadesOtros)
                }
            }
        }
    }
}

@Composable
fun ResumenScreen(
    bebidas: List<String>,
    cantidadesBebidas: List<Int>,
    comidas: List<String>,
    cantidadesComidas: List<Int>,
    otros: List<String>,
    cantidadesOtros: List<Int>
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Resumen de Compra",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        val totalBebidas = bebidas.indices.sumOf { index ->
            val precio = precios[bebidas[index]] ?: 0.0
            precio * cantidadesBebidas[index]
        }
        val totalComidas = comidas.indices.sumOf { index ->
            val precio = precios[comidas[index]] ?: 0.0
            precio * cantidadesComidas[index]
        }
        val totalOtros = otros.indices.sumOf { index ->
            val precio = precios[otros[index]] ?: 0.0
            precio * cantidadesOtros[index]
        }
        val total = (totalBebidas + totalComidas + totalOtros).format(2)

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item { SectionTitle(title = "Bebidas") }
            items(bebidas.size) { index ->
                if (cantidadesBebidas[index] > 0) {
                    val precio = precios[bebidas[index]] ?: 0.0
                    Text(text = "${bebidas[index]}: ${cantidadesBebidas[index]} x ${precio.format(2)} €")
                }
            }
            item { SectionTitle(title = "Comidas") }
            items(comidas.size) { index ->
                if (cantidadesComidas[index] > 0) {
                    val precio = precios[comidas[index]] ?: 0.0
                    Text(text = "${comidas[index]}: ${cantidadesComidas[index]} x ${precio.format(2)} €")
                }
            }
            item { SectionTitle(title = "Otros") }
            items(otros.size) { index ->
                if (cantidadesOtros[index] > 0) {
                    val precio = precios[otros[index]] ?: 0.0
                    Text(text = "${otros[index]}: ${cantidadesOtros[index]} x ${precio.format(2)} €")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total: $total €",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            Button(onClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "Nueva Cuenta")
            }
        }
    }
}

val precios = mapOf(
    "Cervezas" to 1.40,
    "Refrescos" to 1.40,
    "Cafés" to 1.20,
    "Infusiones" to 1.20,
    "Copas Anís" to 1.40,
    "Copas coñac" to 1.40,
    "Chapita Whisky" to 2.50,
    "Copas Martini" to 2.50,
    "Copas Rioja" to 1.50,
    "Botellines" to 1.40,
    "Batidos" to 1.40,
    "Zumos" to 1.40,
    "Cola Cao" to 1.40,
    "Mosto" to 0.80,
    "Tinto" to 1.40,
    "Botella Agua 0,5" to 0.80,
    "Botella Agua 1L" to 1.20,
    "Litronas" to 2.40,
    "Coca Cola 2L" to 2.50,
    "Fanta 2L" to 2.50,
    "Cubalibre Ginebra" to 5.00,
    "Cubalibre Whisky" to 5.00,
    "Cubalibre Ron" to 5.00,
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
    "Queso Racion" to 9.00,
    "Viena" to 0.40,
    "Bollo" to 0.50,
    "Picaito" to 0.50,
    "Hielos" to 1.40,
    "Regaña" to 1.50,
    "Picos" to 1.00
)
