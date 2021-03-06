/*
 *  The Sun Project JXTA(TM) Software License
 *
 *  Copyright (c) 2001-2007 Sun Microsystems, Inc. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without 
 *  modification, are permitted provided that the following conditions are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice, 
 *     this list of conditions and the following disclaimer in the documentation 
 *     and/or other materials provided with the distribution.
 *
 *  3. The end-user documentation included with the redistribution, if any, must 
 *     include the following acknowledgment: "This product includes software 
 *     developed by Sun Microsystems, Inc. for JXTA(TM) technology." 
 *     Alternately, this acknowledgment may appear in the software itself, if 
 *     and wherever such third-party acknowledgments normally appear.
 *
 *  4. The names "Sun", "Sun Microsystems, Inc.", "JXTA" and "Project JXTA" must 
 *     not be used to endorse or promote products derived from this software 
 *     without prior written permission. For written permission, please contact 
 *     Project JXTA at http://www.jxta.org.
 *
 *  5. Products derived from this software may not be called "JXTA", nor may 
 *     "JXTA" appear in their name, without prior written permission of Sun.
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 *  INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND 
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL SUN 
 *  MICROSYSTEMS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT 
 *  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, 
 *  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  JXTA is a registered trademark of Sun Microsystems, Inc. in the United 
 *  States and other countries.
 *
 *  Please see the license information page at :
 *  <http://www.jxta.org/project/www/license.html> for instructions on use of 
 *  the license in source files.
 *
 *  ====================================================================

 *  This software consists of voluntary contributions made by many individuals 
 *  on behalf of Project JXTA. For more information on Project JXTA, please see 
 *  http://www.jxta.org.
 *
 *  This license is based on the BSD license adopted by the Apache Foundation. 
 */

package edu.uci.ics.luci.p2p4java.content;

import java.util.List;

/**
 * The ContentService is intended to be the end-user's
 * main access point when working with Content and Content transfers.
 * It provides the ability to leverage multiple ContentProvider
 * implementations concurrently, providing resilience and failover
 * in the event that one or more ContentProvider implementations
 * fail to perform the requested task(s) properly.
 * <p/>
 * The ability to enumerate the list of providers is in place to allow for
 * programatic removal of providers which may not have been programatically
 * added (e.g., added via the Jar SPI mechanism), default implementation(s)
 * included.
 * <p/>
 * ContentService implementations supporting multiple provider implementations
 * may choose to defer the startup of the providers until the time of first
 * use.
 * 
 * @see edu.uci.ics.luci.p2p4java.content.ContentProvider
 */
public interface ContentService extends ContentProviderSPI {

    /**
     * Adds a provider instance.
     *
     * @param provider instance to register
     */
    void addContentProvider(ContentProviderSPI provider);

    /**
     * Removes a provider instance.  If transfers are already underway by
     * the time this method is called, they will continue to use the provider
     * until the transfer has completed or has been terminated.
     *
     * @param provider instance to remove.
     */
    void removeContentProvider(ContentProvider provider);

    /**
     * Returns a list of content service provider instances underlying this
     * service.  This method returns all registered provider instances,
     * including those which have not been initialized and/or started.  If
     * you wish to obtain a list of only those providers which have successfully
     * started and are available for use, use the
     * {@code getActiveContentProviders()} method form instead.
     *
     * @return list of content providers registered with this service.  If no
     * providers are registered, this method return an empty list.
     */
    List<ContentProvider> getContentProviders();

    /**
     * Returns a list of content service provider instances underlying this
     * service which have been started and are available for use.  Since
     * this method only returns the provider instance which have successfully
     * made it through intialization, it will not be a complete list of all
     * registered providers.  Also, a {@code ContentService} implementation
     * may choose to defer the initialization of it's underlying
     * provider instances (if any) until the time of use.  In this case, this
     * method would return an empty list until after the first use.
     * <p/>
     * ContentService instances which defer the starting of the providers
     * until first use should treat a call to this method as a first use.
     * 
     *
     * @return list of content providers registered with this service.  If no
     *  providers are active, this method returns an empty list.
     */
    List<ContentProvider> getActiveContentProviders();

}
