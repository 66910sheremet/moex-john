from datetime import timedelta, datetime

from airflow import DAG
from airflow.operators.python_operator import PythonOperator
from db_pack.moex2 import fx_moex_currencies

DAG_DEFAULT_ARGS = {
    'owner': 'airflow',
    'depends_on_past': False,
    'retries': 1,
    'retry_delay': timedelta(minutes=1)
}

with DAG('fx_moex_currencies_download', start_date=datetime(2024, 7, 23), schedule_interval="0 7 * * *",
         default_args=DAG_DEFAULT_ARGS, catchup=False) as dag:

    updating_db_daily = PythonOperator(task_id="updating_db_moex_daily", python_callable=fx_moex_currencies.main)

    updating_db_daily
