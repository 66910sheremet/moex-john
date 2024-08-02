import os
from typing import Dict

MAPBOX_API_KEY = os.getenv('MAPBOX_API_KEY', '')
CACHE_CONFIG = {
    'CACHE_TYPE': 'RedisCache',
    'CACHE_DEFAULT_TIMEOUT': 300,
    'CACHE_KEY_PREFIX': 'superset_',
    'CACHE_REDIS_HOST': 'redis',
    'CACHE_REDIS_PORT': 6379,
    'CACHE_REDIS_DB': 1,
    'CACHE_REDIS_URL': 'redis://redis:6379/1'}
FILTER_STATE_CACHE_CONFIG = {
    'CACHE_TYPE': 'RedisCache',
    'CACHE_DEFAULT_TIMEOUT': 86400,
    "CACHE_KEY_PREFIX": "superset_filter_",
    'CACHE_REDIS_URL': 'redis://redis:6379/1'}
EXPLORE_FORM_DATA_CACHE_CONFIG = {
    'CACHE_TYPE': 'RedisCache',
    'CACHE_DEFAULT_TIMEOUT': 86400,
    "CACHE_KEY_PREFIX": "superset_explore_form_",
    'CACHE_REDIS_URL': 'redis://redis:6379/1'}
SQLALCHEMY_DATABASE_URI = 'sqlite:////var/lib/superset/superset.db'
SQLALCHEMY_TRACK_MODIFICATIONS = True
SECRET_KEY = 'thisISaSECRET_1234'
# SUPERSET_SECRET_KEY= 'thisISaSECRET_1234'

CONTENT_SECURITY_POLICY_WARNING = False
PREVENT_UNSAFE_DB_CONNECTIONS = False

TIME_GRAIN_ADDONS: Dict[str, str] = {
    'PT2S': '2 second',
    'PT5S': '5 second',
    'PT10S': '10 second',
    'PT30S': '30 second'
}

TIME_GRAIN_ADDON_EXPRESSIONS: Dict[str, Dict[str, str]] = {
    'druid': {
        'PT2S': '''TIME_FLOOR({col}, 'PT2S')''',
        'PT5S': '''TIME_FLOOR({col},  'PT5S')''',
        'PT10S': '''TIME_FLOOR({col}, 'PT10S')''',
        'PT30S': '''TIME_FLOOR({col}, 'PT30S')'''
    }
}
