/**
 *  Copyright 2013 Zola Madolo (http://people.cs.uct.ac.za/~zmadolo/)
 *	This work is licenced under the Creative Commons Attribution 2.5 South Africa License. 
 *	To view a copy of this licence, visit http://creativecommons.org/licenses/by/2.5/za/ or 
 *	send a letter to Creative Commons, 171 Second Street, 
 *	Suite 300, San Francisco, California 94105, USA.
 *
 *  Visit http://www.rcips.uct.ac.za/ip/copyright/bla/
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/*******************************************************************************
 * Copyright (c) 2013 Zola Madolo (http://people.cs.uct.ac.za/~zmadolo/).
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package phcs.android.service.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONObject;

import phcs.lib.ConnectionHandler;
import phcs.lib.VitalParameter;

/**
 * @author Zola Madolo
 *
 */
public class ServiceClientConnectionHandler extends ConnectionHandler {
	/**
	 * @param socket
	 */
	public ServiceClientConnectionHandler(Socket socket) {
		super(socket);
	}
	@Override
	protected void handle(Socket socket) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		VitalParameter parameter = VitalParameter.getInstance();
		parameter.setName("Temperature Android");
		parameter.addValue("High", 90.0);
		parameter.addValue("Low", 5.0);
		JSONObject object = parameter.convertToJsonObject();
		out.println(object.toString()); 
	}
	@Override
	protected void displayConnectionInfo(Socket socket) {
		
	}
}
