package org.springframework.samples.ppinot.util;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import es.us.isa.ppinot.model.state.BPMNState;
import es.us.isa.ppinot.model.state.GenericState;
import es.us.isa.ppinot.model.state.RuntimeState;

public class RuntimeStateFormatter implements Formatter<RuntimeState> {

	@Override
	public String print(RuntimeState object, Locale locale) {
		if (object == null) {
			return "";
		}
		String result = object.toString();
		return result;
	}

	@Override
	public RuntimeState parse(String text, Locale locale) throws ParseException {
		RuntimeState result = null;

		switch (text) {
		case "START":
			result = GenericState.START;
			break;
		case "END":
			result = GenericState.END;
			break;
		case "READY":
			result = BPMNState.READY;
			break;
		case "ACTIVE":
			result = BPMNState.ACTIVE;
			break;
		case "WITHDRAWN":
			result = BPMNState.WITHDRAWN;
			break;
		case "COMPLETING":
			result = BPMNState.COMPLETING;
			break;
		case "COMPLETED":
			result = BPMNState.COMPLETED;
			break;
		case "FAILING":
			result = BPMNState.FAILING;
			break;
		case "FAILED":
			result = BPMNState.FAILED;
			break;
		case "TERMINATING":
			result = BPMNState.TERMINATING;
			break;
		case "TERMINATED":
			result = BPMNState.TERMINATED;
			break;
		case "COMPENSATING":
			result = BPMNState.COMPENSATING;
			break;
		case "COMPENSATED":
			result = BPMNState.COMPENSATED;
			break;
		case "EXECUTING":
			result = BPMNState.EXECUTING;
			break;
		case "DELETED":
			result = BPMNState.DELETED;
			break;
		}
		return result;
	}
}
