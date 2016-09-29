# Esup-portail Esup-Sympa

This Java Portlet is a [Portlet][] of the [Esup-portail Community] and a [Community Portlet][] of [Apereo uPortal] ecosystem.

The original version of Esup-Sympa [was written by][Sympa-portlet contributors] Oliver Franco of the INSA of Lyon. This JSR-168 portlet allows to display the Sympa mailing lists of a connected user for those he is: subscriber, Moderator or Owner. For each mailing list, a description, the user's rights and a url link to reach directly to the mailing-list User Interface in Sympa Application (if you have the right to) is provided.

## Project Actors
Lead : [Doriane Dusart][] - Valenciennes
Developer : Olivier Franco - INSA de Lyon
Contributors  :
 * [Jean-Pierre Tran][] - Université de Rouen / UNR RUNN
 * [Vincent Bonamy][] - Université de Rouen / UNR RUNN - project Maintainer
 * Olivier Salaün - CRU
 * Claude Viéville - Lille 1
 * Jean-Claude Vergerolle - Bordeaux 2
 * [Christian Cousquer][] - Université Pierre et Marie Curie- Sorbonne Université

## Version Notes
 * 4.5
Specialy designed for `Apereo uPortal 4.3.1+ Respondr Theme` with Responsive Views in the portlet.
The layout of the portlet is designed for mobile /tablet (single or multi-)column(s) /Desktop (single or multi-)column(s) layouts.
Style in LESS
Tested with: Apache Maven 3.3.9, Java version: 1.8.0_91, platform encoding: UTF-8

 * 4.4
Designed for Apereo uPortal 4.0+ Universality and mUniversaty Themes with desktop and mobile Views in the portlet

## Configuration

See also [documentation in the external wiki](https://www.esup-portail.org/wiki/display/PROJCANSYMPA/ESUP-Sympa) of Esup-portail in french or [informations in the external wiki](https://wiki.jasig.org/display/PLT/EsupSympa) of Apereo in the Community Portlet.

## Installation
### Prerequisite
 * Sympa 6.0 or later is required
 * you must to enable sympasoap
 * to avoid encoding problems, be sure to apply the patch from this issue : [#7318][] Encoding problem on symp soap (Fixed in 6.1.22)

### Basic configuration
Just modify `config.properties`, this config file should be well annotated. This config file is sufficient to set only one Sympa server/robot.

### Advanced configuration
You can modify `src/main/webapp/WEB-INF/context/applicationContext.xml`.
In serverList property of the domainService bean, you can add an entry like this for example :

```xml
<entry key="sympa2.monuniv.fr">
 <bean scope="session">
  <property name="adminUrl" value="https://sympa2.monuniv.fr/sympa/net/admin/%l"/>
  <property name="connectUrl" value="https://cas.monuniv.fr/cas/login?service=%s%3Fchecked_cas=1"/>
  <property name="name" value="monuniv.fr"/>
  <property name="newListUrl" value="https://sympa2.monuniv.fr/sympa/net/create_list_request"/>
  <property name="homeUrl" value="https://sympa2.monuniv.fr/sympa/net"/>
  <property name="endPointUrl" value="https://sympa2.monuniv.fr/sympasoap"/>
  <property name="timeout" value="5000"/>
  <property name="credentialRetriever" ref="casCredentialRetrieverImpl"/>
  <property name="indentityRetriever" ref="userIdentityRetrieverImpl"/>
  <property name="cacheManager" ref="cacheManager"/>
  <property name="newListForRoles">
   <set>
    <value>ADMINS</value>
   </set>
  </property>
 </bean>
</entry>
```

### Portlet - uPortal deployment
 * `mvn clean -Dmaven.test.skip=true package` will create a war in `target/esup-portlet-sympa.war`
 * Next from uPortal sources : `ant deployPortletApp -DportletApp=/opt/git/esup-portlet-sympa/target/esup-portlet-sympa.war`
or
 * [the Maven Overlay Strategy][]

[Doriane Dusart]: https://github.com/ddusart
[Vincent Bonamy]: https://github.com/vbonamy
[Jean-Pierre Tran]: https://github.com/jptran
[Christian Cousquer]: https://github.com/cousquer

[Portlet]: https://apps.esup-portail.org/index.php
[Community Portlet]: https://wiki.jasig.org/display/PLT/Community+Portlets
[Esup-portail Community]: https://www.esup-portail.org/
[Sympa-portlet contributors]: https://github.com/EsupPortail/esup-portlet-sympa/graphs/contributors
[Apereo uPortal]: https://www.apereo.org/projects/uportal
[the Maven Overlay Strategy]: https://wiki.jasig.org/display/UPM41/Working+with+Portlet+Overlays

[#7318]: https://sourcesup.cru.fr/tracker/index.php?func=detail&aid=7318&group_id=23&atid=167

