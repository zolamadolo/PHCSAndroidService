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

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * Client, this connects to remote server...
 * @author Zola Madolo
 *
 */
public class ServiceClient {

	private Socket _socket;
	private Thread thread_handler;
	/**
	 * Important as it serves as a listener for remote server feeds
	 */
	private ServiceClientConnectionHandler clientHandler = null;
	/**
	 * Used when creating a Client object...
	 * @param host
	 * @param port
	 */
	public ServiceClient(Socket socket)
	{
		this._socket = socket;
	}
	/**
	 * return instance of ConnectionHandler
	 * @return ClientConnectionHandler
	 */
	public ServiceClientConnectionHandler getClientHandler()
	{
		return clientHandler;
	}
	/**
	 * Connect to remote server and create ConnectionHandler object...
	 * to handler remote server feeds... and local server feeds
	 * @throws UnknownHostException 
	 * @throws IOException
	 * @throws Exception
	 */
	public void connect()
	{
		 clientHandler = new ServiceClientConnectionHandler(_socket);
		 thread_handler = new Thread(clientHandler);
		 thread_handler.start();
	}
	/**
	 * Disconnect Client from remote server.
	 */
	public void disconnect()
	{
		try
		{
			if(_socket!=null)
			{
				_socket.close();
				System.out.println("Client Connection Closed...");
			}
			thread_handler.interrupt();
		}catch(Exception ex)
		{
			//ignore
		}  
	}
}
