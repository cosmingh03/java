<!DOCTYPE html>
<html>
<head>
    <title>Image Repository Report</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .image-card { 
            border: 1px solid #ddd; 
            padding: 15px; 
            margin: 10px 0; 
            border-radius: 5px; 
        }
        .tags { color: #666; }
    </style>
</head>
<body>
    <h1>Image Repository Report</h1>
    
    <h2>Images (${images?size})</h2>
    <#list images as image>
        <div class="image-card">
            <h3>${image.name()}</h3>
            <p>Location: ${image.location()}</p>
            <p>Date: ${image.date()}</p>
            <p class="tags">
                Tags: 
                <#if image.tags()?has_content>
                    ${image.tags()?join(", ")}
                <#else>
                    No tags
                </#if>
            </p>
        </div>
    </#list>
</body>
</html>