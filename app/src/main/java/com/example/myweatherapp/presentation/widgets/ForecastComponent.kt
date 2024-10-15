package com.example.myweatherapp.presentation.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myweatherapp.R
import com.example.myweatherapp.common.DateUtil.toFormattedDate
import com.example.myweatherapp.presentation.theme.MyWeatherAppTheme
import com.example.myweatherapp.common.DateUtil.toFormattedDay

@Composable
fun ForecastComponent(
    modifier: Modifier = Modifier,
    date: String,
    icon: String,
    minTemp: String,
    maxTemp: String,
) {
    ElevatedCard(
        modifier = modifier.padding(end = 16.dp, bottom = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                text = date.toFormattedDay().orEmpty(),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                text = date.toFormattedDate().orEmpty(),
                style = MaterialTheme.typography.titleMedium
            )
            AsyncImage(
                modifier = Modifier.size(42.dp),
                model = stringResource(R.string.icon_image_url, icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.ic_placeholder),
                placeholder = painterResource(id = R.drawable.ic_placeholder),
            )

            Row {
                Text(
                    text = maxTemp,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                )
                Text(text = "/")
                Text(
                    text = minTemp, style = MaterialTheme.typography.bodySmall
                )
            }

        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ForecastComponentPreview() {
    Surface {
        MyWeatherAppTheme {
            ForecastComponent(
                date = "2023-10-07",
                icon = "116.png",
                minTemp = "12",
                maxTemp = "28",
            )
        }
    }
}