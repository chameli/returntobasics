
mwHome=sys.argv[1]
domainHome=sys.argv[2]

print "Reading template"
readTemplate(mwHome + "/wlserver/common/templates/domains/wls.jar")

#=======================================================================================
# Configure the Administration Server and SSL port.
#
# To enable access by both local and remote processes, you should not set the 
# listen address for the server instance (that is, it should be left blank or not set). 
# In this case, the server instance will determine the address of the machine and 
# listen on it. 
#=======================================================================================
print "Configure the Administration Server and SSL port"
cd('Servers/AdminServer')
set('ListenAddress','')
set('ListenPort', 7001)

create('AdminServer','SSL')
cd('SSL/AdminServer')
set('Enabled', 'True')
set('ListenPort', 7002)

#=======================================================================================
# Define the user password for weblogic.
#=======================================================================================
print "Define the user password for weblogic"
cd('/')
cd('Security/base_domain/User/weblogic')
cmo.setPassword('welcome1')

#=======================================================================================
# Create a JMS Server.
#=======================================================================================
print "Create a JMS Server"
cd('/')
create('myJMSServer', 'JMSServer')

#=======================================================================================
# Create a JMS System resource. 
#=======================================================================================
print "Create a JMS System resource"
cd('/')
create('myJmsSystemResource', 'JMSSystemResource')
cd('JMSSystemResource/myJmsSystemResource/JmsResource/NO_NAME_0')

#=======================================================================================
# Create a JMS Queue and its subdeployment.
#=======================================================================================

print "Create a JMS Queue and its subdeployment"
myq=create('myQueue','Queue')
myq.setJNDIName('jms/myqueue')
myq.setSubDeploymentName('myQueueSubDeployment')

cd('/')
cd('JMSSystemResource/myJmsSystemResource')
create('myQueueSubDeployment', 'SubDeployment')

#=======================================================================================
# Create and configure a JDBC Data Source, and sets the JDBC user.
#=======================================================================================

print "Create and configure a JDBC Data Source, and sets the JDBC user"
cd('/')
create('myDataSource', 'JDBCSystemResource')
cd('JDBCSystemResource/myDataSource/JdbcResource/myDataSource')
create('myJdbcDriverParams','JDBCDriverParams')
cd('JDBCDriverParams/NO_NAME_0')
set('DriverName','com.mysql.jdbc.Driver')
set('URL','jdbc:mysql://localhost:3306/returntobasics')
set('PasswordEncrypted', 'returntobasics')
set('UseXADataSourceInterface', 'false')
create('myProps','Properties')
cd('Properties/NO_NAME_0')
create('user', 'Property')
cd('Property/user')
cmo.setValue('returntobasics')

cd('/JDBCSystemResource/myDataSource/JdbcResource/myDataSource')
create('myJdbcDataSourceParams','JDBCDataSourceParams')
cd('JDBCDataSourceParams/NO_NAME_0')
set('JNDIName', java.lang.String("myDataSource_jndi"))

cd('/JDBCSystemResource/myDataSource/JdbcResource/myDataSource')
create('myJdbcConnectionPoolParams','JDBCConnectionPoolParams')
cd('JDBCConnectionPoolParams/NO_NAME_0')
set('TestTableName','SYSTABLES')

#=======================================================================================
# Target resources to the servers. 
#=======================================================================================

print "Target resources to the servers"
cd('/')
assign('JMSServer', 'myJMSServer', 'Target', 'AdminServer')
assign('JMSSystemResource.SubDeployment', 'myJmsSystemResource.myQueueSubDeployment', 'Target', 'myJMSServer')
assign('JDBCSystemResource', 'myDataSource', 'Target', 'AdminServer')

#=======================================================================================
# Write the domain and close the domain template.
#=======================================================================================

print "Write the domain and close the domain template"
setOption('OverwriteDomain', 'true')
writeDomain(domainHome)
closeTemplate()
