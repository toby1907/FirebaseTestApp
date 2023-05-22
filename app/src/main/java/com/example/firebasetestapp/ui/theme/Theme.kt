import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.example.firebasetestapp.ui.theme.*


private val DarkColorPalette = darkColorScheme(
    primary = BrightOrange, onPrimaryContainer = MediumOrange, secondary = DarkOrange
)
private val LightColorPalette = lightColorScheme(
    primary = BrightOrange,
    onPrimaryContainer = MediumOrange,
    secondary = DarkOrange
)

@Composable
fun MakeItSoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

   MaterialTheme(colorScheme = colors, typography = Typography, shapes = Shapes, content = content )
}