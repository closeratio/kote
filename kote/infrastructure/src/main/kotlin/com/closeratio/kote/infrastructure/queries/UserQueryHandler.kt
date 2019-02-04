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

package com.closeratio.kote.infrastructure.queries

import com.closeratio.kote.infrastructure.events.UserCreatedEvent
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserQueryHandler {

	private val ids = hashSetOf<UUID>()

	@EventHandler
	fun handle(event: UserCreatedEvent) {
		ids.add(event.userId)
	}

	@QueryHandler
	fun handle(query: GetAllUserIDsQuery): GetAllUserIDsResponse {
		return GetAllUserIDsResponse(ids)
	}

}