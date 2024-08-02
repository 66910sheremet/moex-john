FROM amancevice/superset:0.35.2

USER root
#COPY ./Storage/superset/superset_config.py /etc/superset/superset_config.py
#COPY ./Storage/superset/superset.db /var/lib/superset/superset.db
#RUN chmod 777 /var/lib/superset/superset.db
#USER superset

COPY ./dockerfile_superset/requirements.txt /superset/requirements.txt

RUN pip install --upgrade --force-reinstall pip \
 && pip install --upgrade --force-reinstall --no-deps -r /superset/requirements.txt \
 && pip install Werkzeug --upgrade \
 && pip install -U Flask-WTF \
 && pip install psycopg2-binary

MAINTAINER Temnikov Dmitry