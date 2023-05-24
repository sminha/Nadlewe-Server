from django.urls import path
from . import views

app_name = 'login'

urlpatterns = [
    path('auth/', views.login, name='login'),
    path('signup/', views.signup, name='signup'),
]