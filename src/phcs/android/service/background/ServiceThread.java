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
package phcs.android.service.background;

import java.net.InetAddress;
import java.net.Socket;

import phcs.android.service.client.ServiceClient;

/**
 * @author Zola Madolo
 *
 */
public class ServiceThread implements Runnable {
	private final String SERVER_IP = "port";
	private final int PORT = 0000;
	/**
	 * 
	 * @param notificationMgn
	 */
	@Override
	public void run() {
		try
		{
			InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
			Socket socket  = new Socket(serverAddr,PORT);
			ServiceClient serviceClient = new ServiceClient(socket);
			serviceClient.connect();
		}catch(Exception ex){
			System.out.println("Could not connect");
		}
	}
}
