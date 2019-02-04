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

package com.closeratio.kote.infrastructure.objects

import com.closeratio.kote.infrastructure.commands.CreateUserCommand
import com.closeratio.kote.infrastructure.commands.DeleteUserCommand
import com.closeratio.kote.infrastructure.commands.UpdateUserDisplayNameCommand
import com.closeratio.kote.infrastructure.commands.UpdateUserEmailAddressCommand
import com.closeratio.kote.infrastructure.events.UserCreatedEvent
import com.closeratio.kote.infrastructure.events.UserDeletedEvent
import com.closeratio.kote.infrastructure.events.UserDisplayNameUpdatedEvent
import com.closeratio.kote.infrastructure.events.UserEmailAddressUpdatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.util.*

@Aggregate
class UserAggregate {

	@AggregateIdentifier
	private lateinit var userId: UUID
	private lateinit var username: String
	private lateinit var displayName: String
	private lateinit var emailAddress: String

	@CommandHandler constructor(command: CreateUserCommand) {
		AggregateLifecycle.apply(UserCreatedEvent(
				command.userId,
				command.username,
				command.displayName,
				command.emailAddress))
	}

	protected constructor() {}

	@EventSourcingHandler
	fun on(event: UserCreatedEvent) {
		userId = event.userId
		username = event.username
		displayName = event.displayName
		emailAddress = event.emailAddress
	}

	@CommandHandler
	fun handle(command: UpdateUserDisplayNameCommand) {
		AggregateLifecycle.apply(UserDisplayNameUpdatedEvent(userId, command.displayName))
	}

	@CommandHandler
	fun handle(command: UpdateUserEmailAddressCommand) {
		AggregateLifecycle.apply(UserEmailAddressUpdatedEvent(userId, command.emailAddress))
	}

	@CommandHandler
	fun handle(command: DeleteUserCommand) {
		AggregateLifecycle.apply(UserDeletedEvent(userId))
	}

	@EventSourcingHandler
	fun on(event: UserDisplayNameUpdatedEvent) {
		displayName = event.newDisplayName
	}

	@EventSourcingHandler
	fun on(event: UserEmailAddressUpdatedEvent) {
		emailAddress = event.newEmailAddress
	}

	@EventSourcingHandler
	fun on(event: UserDeletedEvent) {
		AggregateLifecycle.markDeleted()
	}

	fun userId() = userId

}