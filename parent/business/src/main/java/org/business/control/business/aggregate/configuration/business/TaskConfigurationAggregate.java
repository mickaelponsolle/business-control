package org.business.control.business.aggregate.configuration.business;

import org.business.control.business.aggregate.AbstractAggregate;
import org.business.control.business.command.BusinessCommand;
import org.business.control.business.event.BusinessEvent;
import org.business.control.business.repository.EventRepository;

public class TaskConfigurationAggregate extends AbstractAggregate {

	public TaskConfigurationAggregate(EventRepository eventRepository) {
		super(eventRepository);
	}

	public void apply(BusinessCommand command) {
		eventRepository.store(new BusinessEvent(command));
	}

}
