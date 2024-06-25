package com.example.kosmoschallange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.kosmoschallange.model.RickAndMortyCharacter
import com.example.kosmoschallange.ui.theme.KosmosChallangeTheme
import com.example.kosmoschallange.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KosmosChallangeTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    CharacterListScreen(characterViewModel)
                }
            }
        }
    }
}

@Composable
fun CharacterListScreen(viewModel: CharacterViewModel) {
    val characters by viewModel.characters.collectAsState()

    LazyColumn {
        items(characters) { character ->
            CharacterItem(character)
        }
    }
}

@Composable
fun CharacterItem(character: RickAndMortyCharacter) {
    var isDetailVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(8.dp)) {
        Row {
            Image(
                painter = rememberImagePainter(data = character.image),
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = character.name, style = MaterialTheme.typography.titleMedium)
                Text(text = character.species, style = MaterialTheme.typography.bodyMedium)
                Text(text = character.status, style = MaterialTheme.typography.bodySmall)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { isDetailVisible = !isDetailVisible }) {
            Text(text = if (isDetailVisible) "Ocultar detalle" else "Ver detalle")
        }
        if (isDetailVisible) {
            CharacterDetail(character = character)
        }
    }
}

@Composable
fun CharacterDetail(character: RickAndMortyCharacter) {
    Column(modifier = Modifier.padding(start = 16.dp)) {
        Text(text = "Status: ${character.status}")
        Text(text = "Species: ${character.species}")
        Text(text = "Type: ${character.type}")
        Text(text = "Gender: ${character.gender}")
        Text(text = "Origin: ${character.origin.name}")
        Text(text = "Location: ${character.location.name}")
    }
}
