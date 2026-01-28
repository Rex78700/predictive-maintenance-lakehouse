graph TB
    subgraph "Sources"
        A[ADLS Gen2<br/>Event Hubs<br/>Streaming]
    end
    subgraph "Medallion Layers - Delta Lake"
        B[Bronze<br/>Raw Ingestion]
        C[Silver<br/>Cleansed & Transformed<br/>Delta Live Tables Pipelines]
        D[Gold<br/>Business-Ready<br/>Aggregations]
    end
    subgraph "Governance"
        U[Unity Catalog<br/>Lineage & RBAC]
    end
    subgraph "Consumers"
        E[Power BI<br/>ML Models<br/>Apps]
    end
    subgraph "Cross-Cutting"
        M[Azure Monitor<br/>Log Analytics]
        S[Azure AD<br/>Key Vault<br/>Private Endpoints]
        CI[Azure DevOps CI/CD]
        CO[Auto-Terminate Clusters<br/>ADLS Lifecycle]
    end
    subgraph "Environments"
        DEV[DEV]
        TEST[TEST]
        PROD[PROD]
    end

    A -->|Raw Data| B
    B -->|DLT Pipelines| C
    C -->|DLT Pipelines| D
    U -.->|Governs| B & C & D
    D -->|Curated Data| E
    M -.->|Monitors| B & C & D & CI
    S -.->|Secures| A & B & C & D & E
    CI -.->|Deploys| DEV --> TEST --> PROD
    CO -.->|Optimizes| DEV & TEST & PROD

    classDef azure fill:#0078D4,stroke:#005A9E,stroke-width:2px,color:#fff;
    class A,B,C,D,E,U,M,S,CI,CO,DEV,TEST,PROD azure;
