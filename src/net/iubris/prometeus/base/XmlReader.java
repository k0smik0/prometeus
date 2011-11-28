/*******************************************************************************
 * Copyleft 2011 Massimiliano Leone - k0smik0@logorroici.org .
 * 
 * XmlReader.java is part of Prometeus.
 * 
 * Prometeus is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Prometeus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Prometeus ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.prometeus.base;

import java.io.InputStream;

import org.simpleframework.xml.Serializer;

import com.google.inject.Inject;


import net.iubris.prometeus.base.exceptions.XmlReaderParsingException;
/**
 * Provides an xml mapper inputstream->Class
 * this is auto-injected by roboguice 
 * 
 * @author k0smik0
 *
 * @param <C>
 */
public class XmlReader<C> {
	
	private Serializer serializer;
	
	@Inject
	public XmlReader(Serializer serializer) {
		this.serializer = serializer;
	}
	
	/**
	 * Return an instance of Class<Parameter> specified from an inputstream
	 * 
	 * @param InputStream which you want parse 
	 * @param clazz Class to instance from parsed file
	 * @return an instance of Class<Parameter>
	 */
	public C parse(InputStream inputStream, Class<C> clazz) throws XmlReaderParsingException {
		try {
			return serializer.read(clazz, inputStream);		
		} catch (Exception e) {
			e.printStackTrace();
			throw new XmlReaderParsingException("error to parsing "+ inputStream +" into "+clazz.getCanonicalName());
		}		
	}		
}
