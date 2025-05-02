package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamCalendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun TeamCalendarScreen(){
    Column(
         modifier = Modifier
             .fillMaxSize()
    ) {
        
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CalendarWithEvents(events: List<Event>) {
    // MutableState for current month
    val currentMonth = remember { mutableStateOf(Calendar.getInstance()) }

    // MutableState for selected date events
    val selectedDateEvents = remember { mutableStateOf<List<Event>>(listOf()) }

    // UI Layout
    HorizontalPager(
        count = Int.MAX_VALUE,
        modifier = Modifier.fillMaxSize(),
        state = rememberPagerState(initialPage = Int.MAX_VALUE / 2),
        verticalAlignment = Alignment.Top
    ) { page ->
        val month = currentMonth.value.clone() as Calendar
        month.add(Calendar.MONTH, page - Int.MAX_VALUE / 2)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Display month and year
            Text(
                text = "${month.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())} ${month.get(Calendar.YEAR)}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Calendar controls
            CalendarControls(currentMonth, page)

            // Calendar grid with events
            val daysInMonth = month.getActualMaximum(Calendar.DAY_OF_MONTH)
            val days = (1..daysInMonth).toList()
            CalendarGrid(events, month, selectedDateEvents)

            // Display events for selected date at the bottom
            EventList(selectedDateEvents.value)
        }
    }
}

@Composable
fun CalendarControls(currentMonth: MutableState<Calendar>, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = {
            currentMonth.value.add(Calendar.MONTH, -1)
        }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Previous Month")
        }
        IconButton(onClick = {
            currentMonth.value.add(Calendar.MONTH, 1)
        }) {
            Icon(Icons.Default.ArrowForward, contentDescription = "Next Month")
        }
    }
}

@Composable
fun CalendarGrid(events: List<Event>, currentMonth: Calendar, selectedDateEvents: MutableState<List<Event>>) {
    val daysInMonth = currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOfMonth = currentMonth.clone() as Calendar
    firstDayOfMonth.set(Calendar.DAY_OF_MONTH, 1)
    val startingDayOfWeek = firstDayOfMonth.get(Calendar.DAY_OF_WEEK) - 1 // Adjusting for zero-based indexing
    val days = (1..daysInMonth).toList()
    val paddingDaysBefore = List(startingDayOfWeek) { -1 }
    val paddingDaysAfter = List((7 - (startingDayOfWeek + daysInMonth) % 7) % 7) { -1 }
    val allDays = paddingDaysBefore + days + paddingDaysAfter
    LazyVerticalGrid(GridCells.Fixed(7)) {
        items(allDays.size) { index ->
            val day = allDays[index]
            val textColor = if (day <= 0) Color.Transparent else Color.Black
            val currentDay = if (day <= 0) "" else day.toString()
            // Determine the events for this day
            val eventsForDay = events.filter { event ->
                val eventCalendar = Calendar.getInstance()
                eventCalendar.time = stringToDate(event.date)
                eventCalendar.get(Calendar.DAY_OF_MONTH) == day && eventCalendar.get(Calendar.MONTH) == currentMonth.get(
                    Calendar.MONTH
                )
            }
            // Display dots for each event
            val eventDots = buildString {
                repeat(eventsForDay.size) {
                    append("• ")
                }
            }
            // Display the day and event dots
            Column(
                modifier = Modifier
                    .padding(2.dp)
                    .clickable {
                        // On day click, update selected date events
                        selectedDateEvents.value = eventsForDay
                    }, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = currentDay,
                    color = textColor,
                )
                Text(
                    text = eventDots,
                    color = Color.Blue,
                )
            }
        }
    }
}

@Composable
fun EventList(events: List<Event>) {
    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        events.forEach { event ->
            Text(text = event.id.toString())
        }
    }
}

fun stringToDate(date: String): Date {
    val formated = SimpleDateFormat("yy-MM-dd", Locale.getDefault())
    return try {
        formated.parse(date)!!
    } catch (e: Exception) {
        e.printStackTrace()
        throw Error(e.message)
    }
}