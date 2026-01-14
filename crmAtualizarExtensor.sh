cp /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webApp/src/main/webapp/site /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webappExtensor/src/main/resources/META-INF/ -rf
cp /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webApp/src/main/webapp/images /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webappExtensor/src/main/resources/META-INF/ -rf
cp /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webApp/src/main/webapp/resources /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webappExtensor/src/main/resources/META-INF/ -rf
cp /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webApp/src/main/webapp/WEB-INF/web.xml  /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webappExtensor/src/main/resources/META-INF/web-fragment.xml -f
cp /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webApp/src/main/webapp/WEB-INF/faces-config.xml /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webappExtensor/src/main/resources/META-INF/ -f
cp /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webApp/src/main/webapp/WEB-INF/jetty-web.xml /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webappExtensor/src/main/resources/META-INF/ -f
cp /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webApp/src/main/webapp/WEB-INF/beans.xml /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webappExtensor/src/main/resources/META-INF/ -f
rsync -a \
  --delete \
  --exclude='*/org/carameloCode/erp/crm/config/' \
  /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webApp/src/main/java/ \
  /home/superBits/projetos/carameloCode/source/carameloCodeCRM/webappExtensor/src/main/java/
