/*
 * Copyright 2009-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package info.joseluismartin.remoting;

import java.lang.reflect.InvocationTargetException;

import org.springframework.remoting.support.RemoteInvocation;
import org.springframework.remoting.support.RemoteInvocationExecutor;
import org.springframework.util.Assert;

/**
 * @author Jose Luis Martin - (jlm@joseluismartin.info)
 *
 */
public class ReferenceInvocationExecutor implements RemoteInvocationExecutor {

	/**
	 * {@inheritDoc}
	 */
	public Object invoke(RemoteInvocation invocation, Object targetObject) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		Assert.notNull(invocation, "RemoteInvocation must not be null");
		Assert.notNull(targetObject, "Target object must not be null");
		// replace references with clients.
		Object[] arguments = invocation.getArguments();
		for (int i = 0; i < arguments.length; i++) {
			if (arguments[i] instanceof RemoteReference) {
				arguments[i] = ((RemoteReference) arguments[i]).createRemoteClient();
			}
		}
		return invocation.invoke(targetObject);
	}

}
