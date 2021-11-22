module.exports = {
    devServer: {
        historyApiFallback: true,
        host: 'localhost',
        port: 8080,
        proxy:{
            '/api':{
                target:'http://localhost:8081'
            }
        },
    }
};
