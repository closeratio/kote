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

import com.ccfraser.muirwik.components.MTypographyVariant.h6
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.list.mListItem
import com.ccfraser.muirwik.components.mTypography
import react.RBuilder
import react.RComponent
import react.setState
import styled.styledDiv

class OpenLobbiesList: RComponent<OpenLobbiesListProps, OpenLobbiesListState>() {

	override fun OpenLobbiesListState.init() {
		lobbies = listOf()
	}

	override fun componentDidMount() {
		setState {
			lobbies = props.initialLobbies
		}
	}

	override fun RBuilder.render() {
		styledDiv {
			mTypography("Open lobbies", variant = h6)
			mList {
				state.lobbies.forEach {
					mListItem(
							primaryText = it.name,
							secondaryText = it.description)
				}
			}
		}
	}

}