package com.example.pulselight.ui.elements.buttons

import androidx.compose.foundation.clickable
import com.example.pulselight.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pulselight.ui.backgrounds.getScreenHeightInDp
import com.example.pulselight.ui.elements.onboarding.OnboardingForm
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.pulselight.models.OnboardingEntity
import com.example.pulselight.ui.theme.ButtonColor
import com.example.pulselight.ui.theme.RadioButtonNotSelected

@Composable
fun OnBoardingRadioButton(
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = if (selected) 60.dp else 24.dp, height = 24.dp)
            .clip(RoundedCornerShape(50))
            .background(if (selected) ButtonColor else RadioButtonNotSelected)
    )
}

@Composable
fun RadioButtonGroup(
    entitiesList: List<OnboardingEntity>,
    goToMeasuring: () -> Unit
) {
    val dpHeight = getScreenHeightInDp()
    var (selectedOption, onOptionSelected) = remember {
        mutableStateOf(entitiesList[0])
    }
    var currentIndex by remember {
        mutableIntStateOf(R.string.onboarding_start)
    }

    Column(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dpHeight / 7),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
            OnboardingForm(
                labelText = stringResource(selectedOption.labelText),
                mainText = stringResource(selectedOption.mainText),
                image = selectedOption.image
            )

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 15.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 15.dp)
            ) {
                entitiesList.forEach { index ->
                    OnBoardingRadioButton(
                        selected = index == selectedOption,
                        modifier = Modifier
                            .size(
                                width = if (index == selectedOption) 60.dp else 24.dp,
                                height = 24.dp
                            )
                            .padding(4.dp)
                            .clip(RoundedCornerShape(50))
                            .clickable { onOptionSelected(index) }
                    )
                }
            }
            RegularButton(onClickAction = {
                var index = entitiesList.indexOf(selectedOption)
                if (index == entitiesList.size - 2) {
                    currentIndex = R.string.onboarding_end
                    ++index
                    onOptionSelected(entitiesList[index])
                    selectedOption = entitiesList[index]
                } else if (index < entitiesList.size - 1) {
                    ++index
                    onOptionSelected(entitiesList[index])
                    selectedOption = entitiesList[index]
                    currentIndex = R.string.onboarding_continue
                } else if (index == entitiesList.size - 1) {
                    goToMeasuring()
                }
            }, buttonText = stringResource(id = currentIndex))

        }
    }

}