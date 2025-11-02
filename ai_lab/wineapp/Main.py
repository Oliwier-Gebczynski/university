# to run app - terminal:
# Streamlit run Main.py
import pandas as pd
import streamlit as st
import altair as alt
import pickle
import numpy as np

# This function creates a real-time inference bar chart using Streamlit.
def visualize_confidence_level(quality_prediction):
    data = (quality_prediction[0] * 100).round(2)
    percentage = pd.DataFrame({'Percentage':data,'Quality':['Low', 'Ave', 'High']})

    base = alt.Chart(percentage).mark_bar().encode(
        x = alt.X('Percentage', scale=alt.Scale(domain=[0, 100])),
        y = alt.Y('Quality', sort=None)
    )
    st.altair_chart(base)

    return

# This function gets user input using selectbox and sidebar sliders
# Return type: Pandas data frame.
def get_user_input():
    wine_type = st.sidebar.selectbox("Select Wine type", ("white", "red"))
    fixed_acidity = st.sidebar.slider('fixed acidity', 3.8, 15.9, 7.0)
    volatile_acidity = st.sidebar.slider('volatile acidity', 0.08, 1.58, 0.4)
    citric_acid = st.sidebar.slider('citric acid', 0.0, 1.66, 0.3)
    residual_sugar = st.sidebar.slider('residual_sugar', 0.6, 65.8, 10.4)
    chlorides = st.sidebar.slider('chlorides', 0.009, 0.611, 0.211)
    free_sulfur_dioxide = st.sidebar.slider('free sulfur dioxide', 1, 289, 200)
    total_sulfur_dioxide = st.sidebar.slider('total sulfur dioxide', 6, 440, 150)
    density = st.sidebar.slider('density', 0.98, 1.03, 1.0)
    ph = st.sidebar.slider('pH', 2.72, 4.01, 3.0)
    sulphates = st.sidebar.slider('sulphates', 0.22, 2.0, 1.0)
    alcohol = st.sidebar.slider('alcohol', 8.0, 14.9, 13.4)

    features = {'type': wine_type,
                'fixed acidity': fixed_acidity,
                'volatile acidity': volatile_acidity,
                'citric acid': citric_acid,
                'residual sugar': residual_sugar,
                'chlorides': chlorides,
                'free sulfur dioxide': free_sulfur_dioxide,
                'total sulfur dioxide': total_sulfur_dioxide,
                'density': density,
                'pH': ph,
                'sulphates': sulphates,
                'alcohol': alcohol
                }

    data = pd.DataFrame(features, index=[0])

    return data


# Streamlit page
st.set_page_config(layout="wide",page_title="Wine", page_icon="🍷")

st.write("""
# Wine Quality Prediction ML App 
This app predicts the **Quality of Wine** using wine features input via the side panel 
""")

st.sidebar.image('Wine.png', width=100,)

st.sidebar.header('Wine parameters')  # user input parameter collection with streamlit side bar
user_input_df = get_user_input()

st.subheader('User Input parameters')
st.write(user_input_df)

# Wine type to numeric value conversion
user_input_df.type = user_input_df.type.map({'white': 0, 'red': 1})

# Logistic Regression, all parameters
st.write("Logistic Regression")
with open('models/model_LR.pkl', 'rb') as f:
    model = pickle.load(f)
prediction_proba = model.predict_proba(user_input_df)
visualize_confidence_level(prediction_proba)

st.write("Logistic Regression no pH")
# Logistic Regression, without pH
user_input_pH = user_input_df.drop(columns=['pH'])
with open('models/model_LR_nopH.pkl', 'rb') as f:
    model = pickle.load(f)
prediction_proba2 = model.predict_proba(user_input_pH)
visualize_confidence_level(prediction_proba2)