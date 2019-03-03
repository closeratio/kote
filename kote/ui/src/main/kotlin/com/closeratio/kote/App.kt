/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.closeratio.kote

import com.ccfraser.muirwik.components.MGridSize.Cells12
import com.ccfraser.muirwik.components.mCssBaseline
import com.ccfraser.muirwik.components.mGridContainer
import com.ccfraser.muirwik.components.mGridItem
import com.closeratio.kote.model.Lobby
import kotlinx.css.padding
import kotlinx.css.px
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.css
import styled.styledDiv

class App: RComponent<RProps, RState>() {

	override fun RBuilder.render() {
		styledDiv {
			mCssBaseline()
			navBar()

			mGridContainer {
				css {
					padding(24.px)
				}

				mGridItem(xs = Cells12, sm = Cells12, lg = Cells12, xl = Cells12) {
					openLobbiesList(listOf(
							Lobby("Best lobby", "For best things"),
							Lobby("OK lobby", "For OK things"),
							Lobby("Bad lobby", "For not so good things")
					))
				}

				mGridItem() {
					joinLobbyButton()
				}

				mGridItem() {
					createLobbyButton()
				}
			}
		}
	}

}