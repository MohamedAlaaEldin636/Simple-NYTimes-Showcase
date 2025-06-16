package my.ym.ui_shared.components

import androidx.compose.foundation.OverscrollEffect
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberOverscrollEffect
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch

/**
 * - Represents [PullToRefreshBox] with [LazyColumn] inside it with an additional feature which
 * is on if [pullToRefreshEnabledWhenReachTopAfterIdleScrollOnly].
 *
 * @param pullToRefreshEnabledWhenReachTopAfterIdleScrollOnly if `true` then pull to refresh
 * is only enabled if [LazyColumn] is currently on the starting index after an idle scroll
 * inshallah.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshLazyColumn(
	modifier: Modifier = Modifier,

	pullToRefreshIsRefreshing: Boolean,
	pullToRefreshOnRefresh: () -> Unit,
	pullToRefreshState: PullToRefreshState = rememberPullToRefreshState(),
	pullToRefreshContentAlignment: Alignment = Alignment.TopStart,
	pullToRefreshIndicator: @Composable BoxScope.() -> Unit = {
		PullToRefreshDefaults.Indicator(
			modifier = Modifier.align(Alignment.TopCenter),
			isRefreshing = pullToRefreshIsRefreshing,
			state = pullToRefreshState
		)
	},
	pullToRefreshEnabledWhenReachTopAfterIdleScrollOnly: Boolean = true,

	childToPullToRefreshAndParentToLazyColumn: @Composable BoxScope.(lazyColumnImpl: @Composable () -> Unit) -> Unit = { lazyColumnImpl ->
		Column {
			lazyColumnImpl()
		}
	},

	lazyColumnModifier: Modifier = Modifier,
	lazyColumnState: LazyListState = rememberLazyListState(),
	lazyColumnContentPadding: PaddingValues = PaddingValues(0.dp),
	lazyColumnReverseLayout: Boolean = false,
	lazyColumnVerticalArrangement: Arrangement.Vertical =
		if (!lazyColumnReverseLayout) Arrangement.Top else Arrangement.Bottom,
	lazyColumnHorizontalAlignment: Alignment.Horizontal = Alignment.Start,
	lazyColumnFlingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
	lazyColumnUserScrollEnabled: Boolean = true,
	lazyColumnOverscrollEffect: OverscrollEffect? = rememberOverscrollEffect(),
	lazyColumnContent: LazyListScope.() -> Unit,
) {
	PullToRefreshBox(
		modifier = modifier,
		isRefreshing = pullToRefreshIsRefreshing,
		onRefresh = pullToRefreshOnRefresh,
		state = pullToRefreshState,
		contentAlignment = pullToRefreshContentAlignment,
		indicator = pullToRefreshIndicator,
	) {
		// Used below to enable pull to refresh only if reached index 0 after idle scroll
		// is done inshallah.

		var enablePullToRefresh by remember { mutableStateOf(true) }

		val coroutineScope = rememberCoroutineScope()

		Box(
			modifier = Modifier
				.fillMaxSize()
				.nestedScroll(
					connection = object : NestedScrollConnection {
						override fun onPostScroll(
							consumed: Offset,
							available: Offset,
							source: NestedScrollSource
						): Offset {
							val disableScroll = pullToRefreshEnabledWhenReachTopAfterIdleScrollOnly
									&& available.y > 0f
									&& enablePullToRefresh.not()

							return if (disableScroll) available else Offset.Zero
						}

						override suspend fun onPostFling(
							consumed: Velocity,
							available: Velocity
						): Velocity {
							return super.onPostFling(consumed, available).also {
								if (pullToRefreshEnabledWhenReachTopAfterIdleScrollOnly) {
									coroutineScope.launch {
										awaitFrame()

										enablePullToRefresh = lazyColumnState.canScrollForward
									}
								}
							}
						}
					},
				)
		) {
			childToPullToRefreshAndParentToLazyColumn {
				LazyColumn(
					modifier = lazyColumnModifier,
					state = lazyColumnState,
					contentPadding = lazyColumnContentPadding,
					reverseLayout = lazyColumnReverseLayout,
					verticalArrangement = lazyColumnVerticalArrangement,
					horizontalAlignment = lazyColumnHorizontalAlignment,
					flingBehavior = lazyColumnFlingBehavior,
					userScrollEnabled = lazyColumnUserScrollEnabled,
					overscrollEffect = lazyColumnOverscrollEffect,
					content = lazyColumnContent,
				)
			}
		}
	}
}
