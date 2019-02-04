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

package com.closeratio.kote.application

import com.closeratio.kote.application.beans.AddUserRequest
import com.closeratio.kote.infrastructure.commands.CreateUserCommand
import com.closeratio.kote.infrastructure.objects.UserAggregate
import com.closeratio.kote.infrastructure.queries.GetAllUserIDsQuery
import com.closeratio.kote.infrastructure.queries.GetAllUserIDsResponse
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class UserController(
		private val queryGateway: QueryGateway,
		private val commandGateway: CommandGateway) {

	@GetMapping("/users/all")
	fun getAllUserIDs(): Set<UUID> {
		return queryGateway.query(
				GetAllUserIDsQuery(),
				GetAllUserIDsResponse::class.java)
				.get().ids
	}

	@PostMapping("/users/add")
	fun addUser(@RequestBody request: AddUserRequest): UUID? {
		return commandGateway.send<UserAggregate>(CreateUserCommand(
				UUID.randomUUID(),
				request.username,
				request.displayName,
				request.emailAddress)).get()?.userId()
	}

}