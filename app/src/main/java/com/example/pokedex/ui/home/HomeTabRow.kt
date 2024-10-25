import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.ui.home.HomeTabTitle
import com.example.pokedex.ui.theme.primaryColor


@Composable
fun HomeTabRow(state: Int, onClick: (state: Int) -> Unit) {
    TabRow(selectedTabIndex = state, containerColor = Color.White) {
        Tab(
            text = {
                HomeTabTitle(title = "All Pokemons", selected = state == 0)
            },
            selected = state == 0,
            onClick = {
                onClick(0)
            },
        )
        Tab(
            text = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    HomeTabTitle(
                        title = "Favourites",
                        selected = state == 1,
                    )
                    Spacer(modifier = Modifier.sizeIn(6.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(width = 20.dp, height = 20.dp)
                            .clip(CircleShape)
                            .background(primaryColor),

                        ) {
                        Text(
                            "0",
                            fontSize = 12.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            },
            selected = state == 1,
            onClick = {
                onClick(1)
            },
        )
    }
}
